package com.munywele.fuelrod.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class UserContent(
    @JsonProperty("user")
    val user: User
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @JsonProperty("uuid")
    val uuid: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("role")
    val role: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("messageCost")
    val messageCost: Double,
    @JsonProperty("enabled")
    val enabled: Boolean,
    @JsonProperty("overdraft")
    val overdraft: Boolean,
    @JsonProperty("img")
    val img: String,
    @JsonProperty("createdAt")
    val createdAt: String,
    @JsonProperty("updatedAt")
    val updatedAt: String,
    @JsonProperty("userServices")
    val userServices: Any? = null,

    @JsonProperty("creditInfo")
    val creditInfo: CreditInfo
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreditInfo(
    @JsonProperty("userUuid")
    val userUuid: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("totalCredit")
    val totalCredit: Double,
    @JsonProperty("creditSpent")
    val creditSpent: Double,
    @JsonProperty("creditLeft")
    val creditLeft: Double,
    @JsonProperty("smsLeft")
    val smsLeft: Double,
    @JsonProperty("cost")
    val cost: Double,
    @JsonProperty("overdraft")
    val overdraft: Boolean,
)
