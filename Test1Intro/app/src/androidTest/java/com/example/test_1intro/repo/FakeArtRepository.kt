package com.example.test_1intro.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_1intro.model.ImageResponse
import com.example.test_1intro.roomdb.Art
import com.example.test_1intro.util.Resource

class FakeArtRepository:ArtRepositoryInterface {
    private var arts = mutableListOf<Art>()
    private var artsLiveData = MutableLiveData<List<Art>>(arts)
    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(), 0, 0))
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }

}