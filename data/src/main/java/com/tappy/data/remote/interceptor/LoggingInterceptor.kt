package com.tappy.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import android.util.Log

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("HTTP_LOG", "Request: ${request.method} ${request.url}")

        val response = chain.proceed(request)
        Log.d("HTTP_LOG", "Response: ${response.code} ${response.message}")

        return response
    }
}