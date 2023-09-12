package com.example.s20mvvmcleanachitecture.domain.reponsitory

import com.example.s20mvvmcleanachitecture.data.model.APIResponse
import com.example.s20mvvmcleanachitecture.data.model.Article
import com.example.s20mvvmcleanachitecture.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun getNewHeadLines():Resource<APIResponse>

    suspend fun getSearchNews(stringSearch:String):Resource<APIResponse>

    suspend fun saveNews(article: Article)

    suspend fun deleteNews(article: Article)

    fun getSavedNews(): Flow<List<Article>>
}