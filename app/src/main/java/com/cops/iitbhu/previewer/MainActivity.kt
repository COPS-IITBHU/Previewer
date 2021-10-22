package com.cops.iitbhu.previewer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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

        binding.selectFile.setOnClickListener(View.OnClickListener {
            getUriOfPdf.launch(arrayOf("application/pdf"))
        })
    }


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

}
