package com.steveluland.gamecast.core.network.di

import com.steveluland.gamecast.core.network.interceptor.AuthenticationInterceptor
import com.steveluland.gamecast.core.network.interceptor.FormatInterceptor
import com.steveluland.gamecast.core.network.interceptor.ResponseBodyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    fun provideAuthenticationInterceptor() = AuthenticationInterceptor()

    @Provides
    fun provideFormatInterceptor() = FormatInterceptor()

    @Provides
    fun provideResponseBodyInterceptor() = ResponseBodyInterceptor()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}