package com.munywele.fuelrod.utils

import android.util.Log
import org.json.JSONObject
import retrofit2.Response


object ApiErrorUtil {

    fun parseError(response: Response<*>): String {
        var errorMsg: String = "${response.code()}"
        try {
            val jObjError = response.errorBody()?.string()?.let { JSONObject(it) }
            errorMsg = jObjError?.getString("message")!!
            return "${response.code()}:$errorMsg"
        } catch (e: Exception) {
            Log.e("ERROR", e.message!!)
        }
        return errorMsg
    }
}
