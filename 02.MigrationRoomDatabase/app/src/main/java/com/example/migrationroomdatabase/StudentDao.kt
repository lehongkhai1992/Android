package com.example.migrationroomdatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface StudentDAO {

    @Insert
    suspend fun insertStudent(student: Student)

}