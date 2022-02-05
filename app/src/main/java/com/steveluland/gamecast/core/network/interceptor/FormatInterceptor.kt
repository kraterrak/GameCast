package com.steveluland.gamecast.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class FormatInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("format", "json")
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}