package com.example.hiddencalc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.hiddencalc.adapter.PhotoAdapter
import com.example.hiddencalc.data.Photo
import com.example.hiddencalc.data.PhotosDatabase
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.fragment_photos.btnAdd
import java.io.File
import java.util.*
import kotlin.concurrent.thread

class PhotosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var database: PhotosDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoAdapter = PhotoAdapter()
        rwPhoto.adapter = photoAdapter

        btnAdd.setOnClickListener {

            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .galleryOnly()
                .start()
            //photoAdapter.addPhoto(Photo(fileUri, Date(System.currentTimeMillis()).toString()))
        }

        database = Room.databaseBuilder(
            activity?.applicationContext!!,
            PhotosDatabase::class.java,
            "photo"
        ).build()
        initRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            thread {
                val newItem = Photo(null, fileUri.toString(), Date(System.currentTimeMillis()).toString())
                val newId = database.PhotoDAO().insert(newItem)
                val newPhoto = newItem.copy(
                    id = newId
                )
                activity?.runOnUiThread {
                    photoAdapter.addPhoto(newPhoto)
                }
            }

            //You can get File object from intent
            val file:File = ImagePicker.getFile(data)!!

            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(activity, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        recyclerView = rwPhoto
        photoAdapter = PhotoAdapter()
        loadItemsInBackground()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = photoAdapter
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.PhotoDAO().getAll()
            activity?.runOnUiThread {
                photoAdapter.update(items)
            }
        }
    }
}