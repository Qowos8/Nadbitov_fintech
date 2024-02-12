package com.example.nadbitov_fintech.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    private val apiKey = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequest = original.newBuilder().addHeader("X-API-KEY", apiKey).build()
        return chain.proceed(newRequest)
    }
}