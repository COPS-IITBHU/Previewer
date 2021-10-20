package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment.DIRECTORY_PICTURES
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileOutputStream

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    //Generates bitmap for pdf
    fun generateBitmap(uri: Uri) : Bitmap?{

        try {
            val fileName = "@${System.currentTimeMillis()}"
            val storageDir = appContext.getExternalFilesDir(DIRECTORY_PICTURES)
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

            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)
            val page = pdfRenderer.openPage(0)
            var bitmap: Bitmap = Bitmap.createBitmap(
                page.width,
                page.height,
                Bitmap.Config.ARGB_8888
            )

            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            bitmap = Bitmap.createBitmap(bitmap,0,0,page.width,(page.height)/3)

            page.close()
            pdfRenderer.close()
            fileDescriptor.close()
            return bitmap
        }
        catch (e : Exception){
            println(e)
            return null
        }
    }
}
