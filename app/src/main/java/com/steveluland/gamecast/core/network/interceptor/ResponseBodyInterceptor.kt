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
            val resultsArray = jsonObject.getJSONArray("results")
            val contentType = responseBody.contentType()
            val newBody = resultsArray.toString().toResponseBody(contentType)
            response.newBuilder().body(newBody).build()
        } ?: response
    }
}