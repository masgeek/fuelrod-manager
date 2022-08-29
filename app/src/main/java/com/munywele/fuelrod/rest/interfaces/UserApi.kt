package com.munywele.fuelrod.rest.interfaces

import com.munywele.fuelrod.rest.response.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @GET("v1/user")
    fun users(): Call<List<User>>

    @GET("v1/user/{uuid}")
    fun userDetails(@Path("uuid") uuid: String): Call<User>

    @PUT("v1/user/{uuid}")
    fun updateUser(@Path("uuid") uuid: String, @Body user: User): Call<User>
}