package com.emirkaya.movieapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithToken = originalRequest.newBuilder()
            .header("Authorization", token)
            .build()
        return chain.proceed(requestWithToken)
    }
}
