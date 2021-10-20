package com.cops.iitbhu.previewer

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cops.iitbhu.previewer.lib.Previewer

class MainActivity : AppCompatActivity() {

    private val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectFile: Button = findViewById(R.id.select_file)
        val youtubeLink: EditText =findViewById(R.id.youtube_link)
        val generateYouTubeThumbnail : Button = findViewById(R.id.generate_thumbnail_for_youtube)
        img = findViewById(R.id.img)

        selectFile.setOnClickListener(View.OnClickListener {

            checkPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE_PERMISSION_CODE
            )
        })











    }


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {

                val intent = result.data
                val uri = intent?.data

                if (uri != null) {
                    val bitmap = Previewer.generateBitmap(uri)
                    if (bitmap != null)
                        img.setImageBitmap(bitmap)
                    else
                        Toast.makeText(this,"Unable to generate bitmap!",Toast.LENGTH_SHORT).show()
                }
            }
        }


    //To get URI of pdf
    private fun getUri() {
        val getUriOfFile = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }

        try {
            startForResult.launch(getUriOfFile)
        }
        catch (e: ActivityNotFoundException) {
            println(e)
        }
    }


    private fun checkPermissions(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission already granted!", Toast.LENGTH_SHORT).show()
            getUri()
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
                getUri()
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}