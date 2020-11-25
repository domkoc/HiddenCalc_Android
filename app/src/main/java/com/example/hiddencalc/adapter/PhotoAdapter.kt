package com.example.hiddencalc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddencalc.R
import com.example.hiddencalc.data.Photo
import kotlinx.android.synthetic.main.note_row.view.tvDate
import kotlinx.android.synthetic.main.photo_row.view.*

class PhotoAdapter() : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    val photoItems = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photoItems[position]
        holder.ivPhoto.setImageURI(photo.photoPath)
        holder.tvDate.text = photo.createDate
    }

    override fun getItemCount() =  photoItems.size

    fun addPhoto(photo: Photo) {
        photoItems.add(photo)
        notifyItemInserted(photoItems.lastIndex)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.ivPhoto
        val tvDate = itemView.tvDate
    }
}