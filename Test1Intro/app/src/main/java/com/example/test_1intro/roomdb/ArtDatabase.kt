package com.example.test_1intro.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_1intro.roomdb.Art
import com.example.test_1intro.roomdb.ArtDao

@Database(entities = [Art::class],version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}
