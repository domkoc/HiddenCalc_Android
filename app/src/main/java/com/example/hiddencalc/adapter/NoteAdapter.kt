package com.example.hiddencalc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddencalc.R
import com.example.hiddencalc.data.Note
import kotlinx.android.synthetic.main.note_row.view.*

class NoteAdapter(val context: Context) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val noteItems = mutableListOf<Note>(
        Note("Note1", "2020. okt"),
        Note("Note2", "2020. sept"),
        Note("Note3", "2020. nov")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteItems[position]

        holder.tvNote.text = note.noteText
        holder.tvDate.text = note.createDate
    }

    override fun getItemCount() =  noteItems.size

    fun addNote(note: Note) {
        noteItems.add(note)
        notifyItemInserted(noteItems.lastIndex)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNote = itemView.tvNote
        val tvDate = itemView.tvDate
    }
}