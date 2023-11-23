package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriberDao {
    @Insert
    suspend fun insertSubscriber(subscribers: Subscribers)

    @Update
    suspend fun updateSubscriber(subscribers: Subscribers)

    @Delete
    suspend fun deleteSubscriber(subscribers: Subscribers)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber(): LiveData<List<Subscribers>>
}