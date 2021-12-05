package com.cops.iitbhu.previewer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.cops.iitbhu.previewer.databinding.ActivityBitmapVideoBinding
import com.cops.iitbhu.previewer.lib.Previewer

class BitmapVideo : AppCompatActivity() {
    private lateinit var binding: ActivityBitmapVideoBinding

    private val activityResultLauncherForVideoUri =
        registerForActivityResult(ActivityResultContracts.GetContent()) { videoUri: Uri ->
            Previewer.setVideoThumbnailFromUri(videoUri, binding.image)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBitmapVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateThumbnailOfVideoUri.setOnClickListener {
            activityResultLauncherForVideoUri.launch("video/*")
        }

        binding.generateThumbnailOfVideoUrl.setOnClickListener {
            Previewer.setVideoThumbnailFromUri(
                Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-forest-stream-in-the-sunlight-529-large.mp4"),
                binding.image
            )
        }
    }
}