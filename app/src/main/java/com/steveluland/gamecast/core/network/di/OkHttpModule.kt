package com.steveluland.gamecast.core.network.di

import com.steveluland.gamecast.core.network.interceptor.AuthenticationInterceptor
import com.steveluland.gamecast.core.network.interceptor.FormatInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class OkHttpModule {

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthenticationInterceptor,
        formatInterceptor: FormatInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(formatInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}