package com.steveluland.gamecast.core.network.di

import com.steveluland.gamecast.core.network.interceptor.AuthenticationInterceptor
import com.steveluland.gamecast.core.network.interceptor.FormatInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    fun provideAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }

    @Provides
    fun provideFormatInterceptor(): FormatInterceptor {
        return FormatInterceptor()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}