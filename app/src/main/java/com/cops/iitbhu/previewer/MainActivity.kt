package com.cops.iitbhu.previewer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pdf.setOnClickListener {
            startActivity(Intent(this, PdfToBitmapActivity::class.java))
        }

        binding.youtubeLink.setOnClickListener {
            startActivity(Intent(this, YoutubeVideoToBitmapActivity::class.java))
        }

        binding.videoUri.setOnClickListener {
            startActivity(Intent(this, VideoToBitmapActivity::class.java))
        }
    }
}