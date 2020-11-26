package com.example.hiddencalc.data

import androidx.room.*

@Dao
interface PhotoDAO {
    @Query("SELECT * FROM photo")
    fun getAll(): List<Photo>

    @Insert
    fun insert(photo: Photo): Long

    @Update
    fun update(photo: Photo)

    @Delete
    fun deleteItem(photo: Photo)
}