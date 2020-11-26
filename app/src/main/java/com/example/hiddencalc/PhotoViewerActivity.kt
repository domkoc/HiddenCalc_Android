package com.example.hiddencalc

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_photo_viewer.*

class PhotoViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)
        phPhoto.setImageURI(Uri.parse(intent.getStringExtra(PHOTO_URI)))
        Toast.makeText(this, intent.getStringExtra(PHOTO_DATE), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "PhotoViewerActivity"
        const val PHOTO_URI = "extra.photo_uri"
        const val PHOTO_DATE = "extra.photo_date"
    }
}