package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment.DIRECTORY_DOCUMENTS
import android.os.ParcelFileDescriptor
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import kotlin.math.min

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    /**
     * Generates an image ID from the youtube video link
     * @param youtubeLink
     * @return Image ID
     */
    fun youtubeLinkToImageUrl(youtubeLink: String): String? {
        val regex =
            "^((?:https?:)?//)?((?:www|m)\\.)?(youtube\\.com|youtu.be|youtube-nocookie.com)(/(?:[\\w\\-]+\\?v=|feature=|watch\\?|e/|embed/|v/)?)([\\w\\-]+)(\\S+)?\$"
        return Regex(regex).matchEntire(youtubeLink)?.groupValues?.get(5)
    }

    /**
     * Takes youtube video link, generates the thumbnail for the video and set it in the imageview passed
     * @param youtubeLink Youtube video link
     * @param imageView   ImageView to display the thumbnail
     */
    fun setThumbnailFromYouTubeVideoUrl(youtubeLink: String, imageView: ImageView) {

        val imageId = youtubeLinkToImageUrl(youtubeLink)
        val imageUrl = "https://img.youtube.com/vi/$imageId/0.jpg"

        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    suspend fun getThumbnailFromVideoUrl(youtubeLink: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val imageId = youtubeLinkToImageUrl(youtubeLink)
                val imageUrl = "https://img.youtube.com/vi/$imageId/0.jpg"
                val url = URL(imageUrl)
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bitmap
            } catch (e: IOException) {
                null
            }
        }
    }

    //Generates bitmap for pdf
    fun generateBitmapFromPdf(uri: Uri): Bitmap? {

        try {
            val fileName = "@${System.currentTimeMillis()}"
            val storageDir = appContext.getExternalFilesDir(DIRECTORY_DOCUMENTS)
            val file = File.createTempFile(fileName, ".pdf", storageDir!!)
            val inputStream = appContext.contentResolver.openInputStream(uri)!!
            val outputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var len: Int

            while (inputStream.read(buffer).also { len = it } >= 0) {
                outputStream.write(buffer, 0, len)
            }

            outputStream.close()
            inputStream.close()

            val fileDescriptor =
                ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)
            val page = pdfRenderer.openPage(0)
            var bitmap: Bitmap = Bitmap.createBitmap(
                page.width,
                page.height,
                Bitmap.Config.ARGB_8888
            )

            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            val size = min(page.height, page.width)
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size)

            file.delete()
            page.close()
            pdfRenderer.close()
            fileDescriptor.close()
            return bitmap
        } catch (e: Exception) {
            println(e)
            return null
        }
    }

    fun setVideoThumbnailFromUri(uri: Uri, imageView: ImageView) {
        Glide.with(imageView)
            .load(uri)
            .into(imageView)
    }

}
