package com.example.test_1intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.test_1intro.model.data.ImageResponse
import com.example.test_1intro.model.util.Resource
import com.example.test_1intro.repo.ArtRepositoryInterface
import com.example.test_1intro.room.Art
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtBookViewModel @ViewModelScoped constructor(
    private val artRepository: ArtRepositoryInterface
) : ViewModel() {

    val artList = artRepository.getArts()

    //image Api fragment
    private val _images = MutableLiveData<Resource<ImageResponse>>()
    val imagesList: LiveData<Resource<ImageResponse>>
        get() = _images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = selectedImage

    //art Details Fragment
    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage: LiveData<Resource<Art>>
        get() = insertArtMsg

    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch {
        artRepository.deleteArt(art)
    }

    fun insertArt(art: Art) = viewModelScope.launch {
        artRepository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            insertArtMsg.postValue(
                Resource.error(
                    "please enter name, artisname, year correctly",
                    null
                )
            )
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArtMsg.postValue(Resource.error("please year correctly", null))
            return
        }
        val art = Art(0, name, artistName, yearInt, selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchQuery: String) {
        if (searchQuery.isEmpty()) {
            return
        }
        _images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = artRepository.imageSearch(searchQuery)
            _images.value = response
        }
    }


}