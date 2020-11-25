package com.example.hiddencalc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hiddencalc.adapter.NoteAdapter
import com.example.hiddencalc.data.Note
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.*

class NotesFragment: Fragment() {

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter = NoteAdapter()
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