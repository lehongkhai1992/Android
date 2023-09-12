package com.example.s20mvvmcleanachitecture.domain.usecase

import com.example.s20mvvmcleanachitecture.data.model.APIResponse
import com.example.s20mvvmcleanachitecture.data.util.Resource
import com.example.s20mvvmcleanachitecture.domain.reponsitory.NewsRepository

class GetNewHeadLinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun excuse(): Resource<APIResponse> {
        return newsRepository.getNewHeadLines()
    }
}