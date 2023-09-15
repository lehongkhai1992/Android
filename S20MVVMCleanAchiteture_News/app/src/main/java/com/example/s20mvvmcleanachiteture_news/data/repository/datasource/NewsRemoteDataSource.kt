package com.example.s20mvvmcleanachiteture_news.data.repository.datasource

import com.anushka.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(): Response<APIResponse>
}