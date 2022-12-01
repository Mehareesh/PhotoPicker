package com.meruga.photopicker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val readPermission: Int = 101
    private val REQUEST_CODE: Int = 201

    private val imageList = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Select photos...", Toast.LENGTH_SHORT).show()
            showImagePicker()
        })
    }

    private fun showImagePicker() {
        checkPermission()
        val imagePickerIntent = Intent()
        imagePickerIntent.type = "image/*"
        imagePickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        imagePickerIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(imagePickerIntent, "Select Image"), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                retrieveSelectedImagesFromGallery(data)
            }
        }
    }

    private fun retrieveSelectedImagesFromGallery(data: Intent) {
        val uriData = data.clipData
        if (uriData != null) {
            val itemCount: Int = uriData.itemCount
            var index = 0
            while (index < itemCount) {
                imageList.add(uriData.getItemAt(index).uri)
                index ++
            }
        } else if (data.data != null) {
            val imageURL: String? = data.data!!.path
            imageList.add(Uri.parse(imageURL))
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                readPermission)
        }
    }
}