package com.cops.iitbhu.previewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.cops.iitbhu.previewer.databinding.ActivityPdfToBitmapBinding
import com.cops.iitbhu.previewer.lib.Previewer

class PdfToBitmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfToBitmapBinding

    private val getUriOfPdf = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            val bitmap = Previewer.generateBitmapFromPdf(uri)
            if (bitmap != null)
                binding.image.setImageBitmap(bitmap)
            else
                Toast.makeText(this, "Unable to generate bitmap!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfToBitmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectFile.setOnClickListener{
            getUriOfPdf.launch(arrayOf("application/pdf"))
        }
    }
}