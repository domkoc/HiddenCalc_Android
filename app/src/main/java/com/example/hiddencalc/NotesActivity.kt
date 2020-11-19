package com.example.hiddencalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddencalc.adapter.NoteAdapter
import com.example.hiddencalc.data.Note
import kotlinx.android.synthetic.main.activity_notes.*
import java.util.*

class NotesActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        noteAdapter = NoteAdapter(this)
        rwNote.adapter = noteAdapter

        btnAdd.setOnClickListener {
            noteAdapter.addNote(
                Note(
                    etNote.text.toString(),
                    Date(System.currentTimeMillis()).toString()
                )
            )
        }
    }
}