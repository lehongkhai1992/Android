package com.example.s20mvvmcleanachiteture_news.data.repository

import com.anushka.newsapiclient.data.model.APIResponse
import com.anushka.newsapiclient.data.model.Article
import com.anushka.newsapiclient.data.util.Resource
import com.anushka.newsapiclient.domain.repository.NewsRepository
import com.example.s20mvvmcleanachiteture_news.data.api.NewsAPIService
import com.example.s20mvvmcleanachiteture_news.data.repository.datasource.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource

):NewsRepository {
    override suspend fun getNewsHeadlines(): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadLines())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if (response.isSuccessful){
            response.body()?.let{result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}