package com.munywele.fuelrod.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.munywele.fuelrod.databinding.ActivityMainBinding
import com.munywele.fuelrod.rest.FuelrodApiHelper
import com.munywele.fuelrod.rest.interfaces.UserApi
import com.munywele.fuelrod.rest.response.PaginatedResponse
import com.munywele.fuelrod.rest.response.UserContent
import com.munywele.fuelrod.utils.ApiErrorUtil
import com.munywele.fuelrod.utils.MySharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var prefs: MySharedPrefs
    private lateinit var userApi: UserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prefs = MySharedPrefs(this@MainActivity)
        setContentView(binding.root)

        userApi = FuelrodApiHelper.getInstance(this).create(UserApi::class.java)

        val mapper = ObjectMapper()
        userApi.users().enqueue(object : Callback<PaginatedResponse> {
            override fun onResponse(
                call: Call<PaginatedResponse>,
                response: Response<PaginatedResponse>
            ) {
                if (response.isSuccessful) {
                    val respBody = response.body()
                    if (respBody != null && respBody.content.isNotEmpty()) {
                        val pojos = mapper.convertValue(
                            respBody.content,
                            object : TypeReference<List<UserContent?>?>() {})

                        showToastMessage("${pojos?.size} Users loaded")
                    }

                } else {
                    val result = ApiErrorUtil.parseError(response)
                    showToastMessage(result)
                }
            }

            override fun onFailure(call: Call<PaginatedResponse>, t: Throwable) {
                showToastMessage("Unable to load user list")
            }

        })

    }

    private fun showToastMessage(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}