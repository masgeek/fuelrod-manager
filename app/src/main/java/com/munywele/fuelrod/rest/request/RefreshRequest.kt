package com.munywele.fuelrod.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshRequest(
    @get:JsonProperty("refreshToken")
    val refreshToken: String
)