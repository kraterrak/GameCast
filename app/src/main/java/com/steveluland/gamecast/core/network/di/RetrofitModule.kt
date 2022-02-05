package com.steveluland.gamecast.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://www.giantbomb.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}