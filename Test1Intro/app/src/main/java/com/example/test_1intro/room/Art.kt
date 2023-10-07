package com.example.test_1intro.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art")
data class Art(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl: String,
)