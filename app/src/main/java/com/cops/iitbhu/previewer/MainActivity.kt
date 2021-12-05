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

        binding.pdf.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, BitmapPDF::class.java))
        })

        binding.youtubeLink.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, BitmapYoutube::class.java))
        })

        binding.videoUri.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, BitmapVideo::class.java))
        })
    }
}