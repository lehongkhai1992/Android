package com.example.test_1intro.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Art::class], version = 1, exportSchema = false)
abstract class ArtDataBase:RoomDatabase() {
    abstract fun artDao() : ArtDao
}