package com.example.test_1intro.repo

import androidx.lifecycle.LiveData
import com.example.test_1intro.model.data.ImageResponse
import com.example.test_1intro.model.util.Resource
import com.example.test_1intro.room.Art
import retrofit2.Response
import retrofit2.http.Query

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArts():LiveData<List<Art>>

    suspend fun imageSearch(searchQuery: String): Resource<ImageResponse>
}