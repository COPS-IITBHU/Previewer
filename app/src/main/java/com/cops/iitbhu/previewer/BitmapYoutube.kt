package com.cops.iitbhu.previewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cops.iitbhu.previewer.databinding.ActivityBitmapYoutubeBinding
import com.cops.iitbhu.previewer.lib.Previewer

class BitmapYoutube : AppCompatActivity() {
    private lateinit var binding: ActivityBitmapYoutubeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBitmapYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateThumbnailForYoutube.setOnClickListener {
            val youtubeLink = binding.youtubeLink.text.toString()
            if (youtubeLink.isNotEmpty()) {
                setThumbnail(youtubeLink)
//                Previewer.setThumbnailFromYouTubeVideoUrl(youtubeLink, binding.img)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setThumbnail(youtubeLink: String) {
        lifecycleScope.launchWhenCreated {
            val bitmap = Previewer.getThumbnailFromVideoUrl(youtubeLink)
            binding.image.setImageBitmap(bitmap)
        }
    }
}