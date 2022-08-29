package com.munywele.fuelrod.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaginatedResponse(
    @JsonProperty("content")
    val content: List<*>,
    @JsonProperty("totalPages")
    val totalPages: Int,
    @JsonProperty("totalElements")
    val totalElements: Int,
    @JsonProperty("last")
    val last: Boolean,
    @JsonProperty("first")
    val first: Boolean,
    @JsonProperty("numberOfElements")
    val numberOfElements: Int,
    @JsonProperty("number")
    val number: Int,
    @JsonProperty("size")
    val size: Int,
    @JsonProperty("empty")
    val empty: Boolean,
)