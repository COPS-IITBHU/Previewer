package com.cops.iitbhu.previewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.cops.iitbhu.previewer.databinding.ActivityBitmapPdfBinding
import com.cops.iitbhu.previewer.lib.Previewer

class BitmapPDF : AppCompatActivity() {
    private lateinit var binding: ActivityBitmapPdfBinding

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
        binding = ActivityBitmapPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectFile.setOnClickListener(View.OnClickListener {
            getUriOfPdf.launch(arrayOf("application/pdf"))
        })
    }
}