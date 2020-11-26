package com.example.hiddencalc.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddencalc.R
import com.example.hiddencalc.data.Note
import com.example.hiddencalc.data.Photo
import kotlinx.android.synthetic.main.note_row.view.tvDate
import kotlinx.android.synthetic.main.photo_row.view.*

class PhotoAdapter internal constructor(private val listener: OnPhotoSelectedListener?) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    interface OnPhotoSelectedListener {
        fun onPhotoSelected(photo: String, date: String)
    }

    val photoItems = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photoItems[position]
        holder.ivPhoto.setImageURI(Uri.parse(photo.photoPath))
        holder.tvDate.text = photo.createDate
        holder.imageUri = photo.photoPath
    }

    override fun getItemCount() =  photoItems.size

    fun addPhoto(photo: Photo) {
        photoItems.add(photo)
        notifyItemInserted(photoItems.lastIndex)
    }

    fun update(photos: List<Photo>) {
        photoItems.clear()
        photoItems.addAll(photos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.ivPhoto
        val tvDate = itemView.tvDate
        var imageUri: String? = null
        init {
            itemView.setOnClickListener { listener?.onPhotoSelected(imageUri!!, tvDate.text.toString())}
        }
    }
}