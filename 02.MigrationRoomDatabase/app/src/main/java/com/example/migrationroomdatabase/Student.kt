package com.example.migrationroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_info")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val id : Int,

    @ColumnInfo(name = "student_name")
    var name : String,

    @ColumnInfo(name = "email_name", defaultValue = "no email")
    var email : String,

    @ColumnInfo(name = "course_name")
    var course : String? = null,
)
