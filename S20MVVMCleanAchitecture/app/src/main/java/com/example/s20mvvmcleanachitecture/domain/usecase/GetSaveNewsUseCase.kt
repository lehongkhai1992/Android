package com.example.s20mvvmcleanachitecture.domain.usecase

import com.example.s20mvvmcleanachitecture.data.model.Article
import com.example.s20mvvmcleanachitecture.domain.reponsitory.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetSaveNewsUseCase(private val newsRepository: NewsRepository) {
    fun excuse(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}