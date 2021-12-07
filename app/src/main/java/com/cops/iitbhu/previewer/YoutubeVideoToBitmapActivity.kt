package com.cops.iitbhu.previewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cops.iitbhu.previewer.databinding.ActivityYoutubeVideoToBitmapBinding
import com.cops.iitbhu.previewer.lib.Previewer

class YoutubeVideoToBitmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeVideoToBitmapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeVideoToBitmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateThumbnailForYoutubeSuspendFunc.setOnClickListener {
            val youtubeLink = binding.youtubeLink.text.toString()
            if (youtubeLink.isNotEmpty()) {
                setThumbnail(youtubeLink)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }

        binding.generateThumbnailForYoutubeNonSuspendFunc.setOnClickListener{
            val youtubeLink = binding.youtubeLink.text.toString()
            if (youtubeLink.isNotEmpty()) {
                Previewer.setThumbnailFromYouTubeVideoUrl(youtubeLink, binding.image)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setThumbnail(youtubeLink: String) {
        lifecycleScope.launchWhenCreated {
            val bitmap = Previewer.getThumbnailFromYoutubeVideoUrl(youtubeLink)
            binding.image.setImageBitmap(bitmap)
        }
    }
}