package com.steveluland.gamecast.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class ResponseBodyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response.body?.let { responseBody ->
            val jsonObject = JSONObject(responseBody.string())
            val resultsObject = jsonObject.get("results")
            val contentType = responseBody.contentType()
            val newBody = resultsObject.toString().toResponseBody(contentType)
            response.newBuilder().body(newBody).build()
        } ?: response
    }
}