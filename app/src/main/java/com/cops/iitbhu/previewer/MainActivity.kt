package com.cops.iitbhu.previewer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {


    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val youtubeLink: EditText = findViewById(R.id.youtube_link)
        val generateYouTubeThumbnail: Button = findViewById(R.id.generate_thumbnail_for_youtube)
        img = findViewById(R.id.img)

        generateYouTubeThumbnail.setOnClickListener {
            val youtubeLink = youtubeLink.text.toString()

            if (youtubeLink.isNotEmpty()) {
                Previewer.thumbnailFromYoutube(img, youtubeLink)
            } else {
                Toast.makeText(this, "Please enter valid Youtube URL", Toast.LENGTH_SHORT).show()
            }
        }
    }


}