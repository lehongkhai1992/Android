package com.example.test_1intro.api

import androidx.room.PrimaryKey
import com.example.test_1intro.model.data.ImageResponse
import com.example.test_1intro.model.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtRetrofitAPI {
    @GET("/api/")
    suspend fun imageSearch(
        @Query(value = "q") searchQuery: String,
        @Query(value = "key") apiKey: String = API_KEY,
    ): Response<ImageResponse>
}