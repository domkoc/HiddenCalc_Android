package com.example.hiddencalc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.hiddencalc.adapter.NoteAdapter
import com.example.hiddencalc.data.Note
import com.example.hiddencalc.data.NotesDatabase
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.*
import kotlin.concurrent.thread


class NotesFragment: Fragment(), NoteAdapter.OnNoteSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var database: NotesDatabase
    private var firstText = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter = NoteAdapter(this)
        rwNote.adapter = noteAdapter

        btnAdd.setOnClickListener {
            thread {
                val newItem = Note(
                    null,
                    etNote.text.toString(),
                    Date(System.currentTimeMillis()).toString()
                )
                val newId = database.NoteDAO().insert(newItem)
                val newNote = newItem.copy(
                    id = newId
                )
                activity?.runOnUiThread {
                    noteAdapter.addNote(newNote)
                    etNote.text.clear()
                }
            }
        }

        etNote.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                if (firstText) {
                    etNote.text.clear()
                    firstText = false
                }
            }
        }


        database = Room.databaseBuilder(
            activity?.applicationContext!!,
            NotesDatabase::class.java,
            "note"
        ).build()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = rwNote
        noteAdapter = NoteAdapter(this)
        loadItemsInBackground()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = noteAdapter
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.NoteDAO().getAll()
            activity?.runOnUiThread {
                noteAdapter.update(items)
            }
        }
    }

    override fun onNoteSelected(note: String, date: String) {
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(context?.applicationContext!!, NoteViewerActivity::class.java)
        showDetailsIntent.putExtra(NoteViewerActivity.NOTE_TEXT, note)
        showDetailsIntent.putExtra(NoteViewerActivity.NOTE_DATE, date)
        startActivity(showDetailsIntent)

    }
}