package com.example.test_1intro.repo

import androidx.lifecycle.LiveData
import com.example.test_1intro.api.ArtRetrofitAPI
import com.example.test_1intro.model.data.ImageResponse
import com.example.test_1intro.model.util.Resource
import com.example.test_1intro.room.Art
import com.example.test_1intro.room.ArtDao
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val artRetrofitAPI: ArtRetrofitAPI
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) = artDao.insertArt(art)

    override suspend fun deleteArt(art: Art) = artDao.deleteArt(art)

    override fun getArts(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun imageSearch(searchQuery: String): Resource<ImageResponse> {
        return try {
            val response = artRetrofitAPI.imageSearch(searchQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data", null)
        }
    }


}