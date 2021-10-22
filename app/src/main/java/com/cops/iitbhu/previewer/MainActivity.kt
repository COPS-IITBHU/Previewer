package com.cops.iitbhu.previewer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.generateThumbnailForYoutube.setOnClickListener {
            val youtubeLink = binding.youtubeLink.text.toString()

            if (youtubeLink.isNotEmpty()) {
                Previewer.setThumbnailFromYouTubeVideoUrl(youtubeLink, binding.img)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
