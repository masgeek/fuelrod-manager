package com.munywele.fuelrod.rest.interfaces

import com.munywele.fuelrod.rest.request.AuthRequest
import com.munywele.fuelrod.rest.response.AuthResponse
import com.munywele.fuelrod.rest.response.User
import retrofit2.Call
import retrofit2.http.*

interface AuthApi {

    @POST("v1/account/auth")
    fun auth(@Body authRequest: AuthRequest): Call<AuthResponse>
}