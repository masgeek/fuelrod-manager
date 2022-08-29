package com.munywele.fuelrod.utils

import android.content.Context
import android.content.SharedPreferences
import com.munywele.fuelrod.rest.response.AuthResponse

class MySharedPrefs(context: Context) {
    companion object {
        private const val PREF_NAME = "fuelrod-pref"
    }

    private val editor: SharedPreferences.Editor
    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    init {
        editor = pref.edit()
    }

    fun saveAuthData(resp: AuthResponse) {
        editor.putString("accessToken", resp.accessToken)
        editor.putString("refreshToken", resp.refreshToken)
        editor.putLong("tokenExpiry", resp.expiry)
        editor.commit()
    }

    fun fetchAuthToken(): String {
        return pref.getString("accessToken", "")!!
    }

    fun fetchRefreshToken(): String {
        return pref.getString("refreshToken", "")!!
    }

    fun tokenValid(): Boolean {
        val unixTime = System.currentTimeMillis() / 1000L
        val tokenTime = pref.getLong("tokenExpiry", 0)
        return tokenTime > unixTime
    }


}
