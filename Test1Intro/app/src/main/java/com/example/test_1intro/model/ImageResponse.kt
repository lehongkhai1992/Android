package com.example.test_1intro.model

data class ImageResponse(
        val hits: List<ImageResult>,
        val total: Int,
        val totalHits: Int
)
