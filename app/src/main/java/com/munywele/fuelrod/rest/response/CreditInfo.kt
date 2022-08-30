package com.munywele.fuelrod.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

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
) {

}
