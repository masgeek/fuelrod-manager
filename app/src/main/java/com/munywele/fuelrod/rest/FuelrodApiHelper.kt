package com.munywele.fuelrod.rest

import android.content.Context
import com.munywele.fuelrod.rest.interceptors.AuthorizationInterceptor
import com.munywele.fuelrod.rest.request.AuthRequest
import com.munywele.fuelrod.rest.response.AuthResponse
import com.munywele.fuelrod.rest.response.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.*


interface FuelrodApiHelper {

    companion object {

        fun getInstance(context: Context, BASE_URL: String = "https://api.tsobu.co.ke/"): Retrofit {

            val builder = OkHttpClient.Builder()

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.networkInterceptors().add(httpLoggingInterceptor)
            builder.addInterceptor(AuthorizationInterceptor(context))

            val client = builder.build()

            return Retrofit.Builder()
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        }
    }

}
