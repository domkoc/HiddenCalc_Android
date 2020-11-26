package com.example.hiddencalc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddencalc.R
import com.example.hiddencalc.data.Note
import kotlinx.android.synthetic.main.note_row.view.*
import java.util.*

class NoteAdapter internal constructor(private val listener: OnNoteSelectedListener?) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    interface OnNoteSelectedListener {
        fun onNoteSelected(note: String, date: String)
    }

    val noteItems = mutableListOf<Note>(/*
        Note("Note1", "2020. okt"),
        Note("Note2", "2020. sept"),
        Note("Note3", "2020. nov")
    */)

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

    fun update(notes: List<Note>) {
        noteItems.clear()
        noteItems.addAll(notes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNote = itemView.tvNote
        val tvDate = itemView.tvDate
        init {
            itemView.setOnClickListener { listener?.onNoteSelected(tvNote.text.toString(), tvDate.text.toString())}
        }
    }
}