package com.example.s20mvvmcleanachiteture_news.data.repository.datasourceImpl

import com.anushka.newsapiclient.data.model.APIResponse
import com.example.s20mvvmcleanachiteture_news.data.api.NewsAPIService
import com.example.s20mvvmcleanachiteture_news.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
    private val country: String,
    private val page: Int
) : NewsRemoteDataSource {
    override suspend fun getTopHeadLines(): Response<APIResponse> {
        return newsAPIService.getTopHeadLines(country, page)
    }

}