package com.steveluland.gamecast.characterlist

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class CharacterListAPITest {

    private lateinit var mockWebServer: MockWebServer

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .build()
        .create(CharacterListAPI::class.java)

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun testfunction() = runTest {

        mockWebServer.enqueue(MockResponse())
        api.fetchCharacterList()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}