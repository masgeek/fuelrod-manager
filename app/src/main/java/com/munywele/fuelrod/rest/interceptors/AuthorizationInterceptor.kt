package com.munywele.fuelrod.rest.interceptors

import android.content.Context
import com.munywele.fuelrod.utils.MySharedPrefs
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(context: Context) : Interceptor {

    private val sessionManager = MySharedPrefs(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val uri = chain.request().url().uri().path
        // If token has been saved, add it to the request

        if (!uri.contains("/account/")) { //skip adding auth token for account path requests
            sessionManager.fetchAuthToken().let { jwtToken ->
                requestBuilder.addHeader("Authorization", "Bearer $jwtToken")
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}