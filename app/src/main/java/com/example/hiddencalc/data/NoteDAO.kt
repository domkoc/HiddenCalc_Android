package com.example.hiddencalc.data

import androidx.room.*

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Delete
    fun deleteItem(note: Note)
}
