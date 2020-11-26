package com.example.hiddencalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.note_row.*

class NoteViewerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_viewer)
        tvNote.text = intent.getStringExtra(NOTE_TEXT)
        Toast.makeText(this, intent.getStringExtra(NOTE_DATE), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "NoteViewerActivity"
        const val NOTE_TEXT = "extra.note_text"
        const val NOTE_DATE = "extra.note_date"
    }
}