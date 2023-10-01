package com.example.s20mvvmcleanachiteture_news.data.api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NewsApiServiceTest {
    /*create instance for newsAPIService and Mockwebserver*/
    lateinit var service:NewsAPIService
    lateinit var server:MockWebServer

    /*create server and service in setup function*/
    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)

    }

    /*shut down server*/
    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockWebServer(fileName:String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
}