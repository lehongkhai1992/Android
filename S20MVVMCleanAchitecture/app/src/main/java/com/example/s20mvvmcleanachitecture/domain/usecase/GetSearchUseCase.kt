package com.example.s20mvvmcleanachitecture.domain.usecase

import com.example.s20mvvmcleanachitecture.data.model.APIResponse
import com.example.s20mvvmcleanachitecture.data.util.Resource
import com.example.s20mvvmcleanachitecture.domain.reponsitory.NewsRepository

class GetSearchUseCase(private val newsRepository: NewsRepository) {
    suspend fun excuse(queryString:String): Resource<APIResponse> {
        return newsRepository.getSearchNews(queryString)
    }
}