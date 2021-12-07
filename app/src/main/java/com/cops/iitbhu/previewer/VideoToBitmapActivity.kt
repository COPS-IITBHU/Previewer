package com.cops.iitbhu.previewer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.cops.iitbhu.previewer.databinding.ActivityVideoToBitmapBinding
import com.cops.iitbhu.previewer.lib.Previewer

class VideoToBitmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoToBitmapBinding

    private val activityResultLauncherForVideoUri =
        registerForActivityResult(ActivityResultContracts.GetContent()) { videoUri: Uri ->
            lifecycleScope.launchWhenCreated {
                val bitmap = Previewer.getThumbnailFromLocalVideoUri(videoUri)
                binding.image.setImageBitmap(bitmap)
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoToBitmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectLocalVideo.setOnClickListener {
            activityResultLauncherForVideoUri.launch("video/*")
        }

        binding.remoteVideo.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val bitmap = Previewer.getThumbnailFromRemoteVideoUri(Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-forest-stream-in-the-sunlight-529-large.mp4%22"))
                binding.image.setImageBitmap(bitmap)
            }
        }
    }
}