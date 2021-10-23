package com.cops.iitbhu.previewer

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {
    private lateinit var btnVideoThumbnail: Button
    private lateinit var imgThumbnail: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgThumbnail = findViewById(R.id.imgThumbnail)
        btnVideoThumbnail = findViewById(R.id.btnVideoThumbnail)
        btnVideoThumbnail.setOnClickListener(View.OnClickListener { view ->
            activityLauncher.launch("video/*")
        })
    }

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uriVideo: Uri ->
            Previewer.getVideoThumbnail(uriVideo, imgThumbnail)
        }
}