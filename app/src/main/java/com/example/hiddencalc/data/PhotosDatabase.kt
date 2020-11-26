package com.example.hiddencalc.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun PhotoDAO(): PhotoDAO
}