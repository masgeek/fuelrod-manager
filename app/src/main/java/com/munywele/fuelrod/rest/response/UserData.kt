package com.munywele.fuelrod.rest.response

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize


@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class UserContent(
    @JsonProperty("user")
    val user: User
) : Parcelable

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class User(
    @JsonProperty("uuid")
    val uuid: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("role")
    val role: String,
    @JsonProperty("name")
    val fullName: String,
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
    val userServices: UserServices? = null,

    @JsonProperty("creditInfo")
    val creditInfo: CreditInfo
) : Parcelable

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
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
) : Parcelable


@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class UserServices(
    @JsonProperty("sender")
    val sender: String,
) : Parcelable
