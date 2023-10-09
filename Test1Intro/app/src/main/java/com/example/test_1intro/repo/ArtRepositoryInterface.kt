package com.example.test_1intro.repo

import androidx.lifecycle.LiveData
import com.example.test_1intro.model.ImageResponse
import com.example.test_1intro.roomdb.Art
import com.example.test_1intro.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}