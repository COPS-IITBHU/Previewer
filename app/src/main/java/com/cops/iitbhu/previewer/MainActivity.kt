package com.cops.iitbhu.previewer

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding
import com.cops.iitbhu.previewer.lib.Previewer
import androidx.lifecycle.lifecycleScope
import com.cops.iitbhu.previewer.databinding.ActivityMainBinding
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val getUriOfPdf =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->

            if (uri != null) {
                val bitmap = Previewer.generateBitmapFromPdf(uri)
                if (bitmap != null)
                    binding.img.setImageBitmap(bitmap)
                else
                    Toast.makeText(this, "Unable to generate bitmap!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectFile.setOnClickListener(View.OnClickListener {
            getUriOfPdf.launch(arrayOf("application/pdf"))
        })

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
            binding.img.setImageBitmap(bitmap)
        }
    }
}