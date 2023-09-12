package com.example.s20mvvmcleanachitecture.domain.usecase

import com.example.s20mvvmcleanachitecture.data.model.Article
import com.example.s20mvvmcleanachitecture.domain.reponsitory.NewsRepository

class DeleteSaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun excuse(article: Article) = newsRepository.deleteNews(article)
}