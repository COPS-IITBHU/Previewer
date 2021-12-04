package com.cops.iitbhu.previewer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val activityResultLauncherForVideoUri =
//        registerForActivityResult(ActivityResultContracts.GetContent()) { videoUri: Uri ->
//            Previewer.setVideoThumbnailFromUri(videoUri, binding.imgThumbnail)
//        }

    private val getUriOfPdf =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->

            if (uri != null) {
                val bitmap = Previewer.generateBitmapFromPdf(uri)
                if (bitmap != null)
                    binding.img.setImageBitmap(bitmap)
                else
                    Toast.makeText(this, "Unable to generate bitmap!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectFile.setOnClickListener(View.OnClickListener {
            getUriOfPdf.launch(arrayOf("application/pdf"))
        })

        binding.generateThumbnailForYoutube.setOnClickListener {
            val youtubeLink = binding.youtubeLink.text.toString()

            if (youtubeLink.isNotEmpty()) {
                setThumbnail(youtubeLink)
//                Previewer.setThumbnailFromYouTubeVideoUrl(youtubeLink, binding.img)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.btnVideoThumbnailFromUri.setOnClickListener {
//            activityResultLauncherForVideoUri.launch("video/*")
//        }
//
//        binding.btnVideoThumbnailFromUrl.setOnClickListener {
//            Previewer.setVideoThumbnailFromUri(
//                Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-forest-stream-in-the-sunlight-529-large.mp4"),
//                binding.imgThumbnail
//            )
//        }
    }

    private fun setThumbnail(youtubeLink: String) {
        lifecycleScope.launchWhenCreated {
            val bitmap = Previewer.getThumbnailFromVideoUrl(youtubeLink)
            binding.img.setImageBitmap(bitmap)
        }
    }
}