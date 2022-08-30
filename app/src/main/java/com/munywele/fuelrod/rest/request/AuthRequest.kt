package com.munywele.fuelrod.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class AuthRequest(
    @get:JsonProperty("username")
    val username: String,
    @get:JsonProperty("password")
    val password: String,
)
