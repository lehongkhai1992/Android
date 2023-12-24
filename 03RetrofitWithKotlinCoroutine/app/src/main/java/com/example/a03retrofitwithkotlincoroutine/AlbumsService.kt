package com.example.a03retrofitwithkotlincoroutine

import retrofit2.Response
import retrofit2.http.GET

interface AlbumsService {

    @GET(value = "/albums")
    suspend fun getAlbums(): Response<Album>
}