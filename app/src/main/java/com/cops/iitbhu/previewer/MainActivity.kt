package com.cops.iitbhu.previewer

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val activityResultLauncherForVideoUri =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uriVideo: Uri ->
            Previewer.setVideoThumbnailFromUri(uriVideo, binding.imgThumbnail)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnVideoThumbnailFromUri.setOnClickListener(View.OnClickListener { view ->
            activityResultLauncherForVideoUri.launch("video/*")
        })

        binding.btnVideoThumbnailFromUrl.setOnClickListener(View.OnClickListener { view ->
            Previewer.setVideoThumbnailFromUri(
                Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-forest-stream-in-the-sunlight-529-large.mp4"),
                binding.imgThumbnail
            )
        })
    }
}