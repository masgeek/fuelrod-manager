package com.munywele.fuelrod.views

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.munywele.fuelrod.databinding.ActivityLoginBinding

import com.munywele.fuelrod.rest.FuelrodApiHelper
import com.munywele.fuelrod.rest.interfaces.AuthApi
import com.munywele.fuelrod.rest.request.AuthRequest
import com.munywele.fuelrod.rest.response.AuthResponse
import com.munywele.fuelrod.utils.ApiErrorUtil
import com.munywele.fuelrod.utils.MySharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefs: MySharedPrefs
    private lateinit var authApi: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        prefs = MySharedPrefs(this@LoginActivity)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (prefs.tokenValid()) {
            startMainActivity()
        }

        binding.login.setOnClickListener {
            val username = binding.username.text
            val password = binding.password.text
            binding.loading.visibility = View.VISIBLE
            val authRequest =
                AuthRequest(username = username.toString(), password = password.toString())

            authUser(authRequest)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        Animatoo.animateSwipeLeft(this@LoginActivity)
    }


    private fun authUser(authRequest: AuthRequest) {
        authApi = FuelrodApiHelper.getInstance(this@LoginActivity).create(AuthApi::class.java)

        val authUser = authApi.auth(authRequest)

        authUser.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    val respBody = response.body()
                    if (respBody != null) {
                        prefs.saveAuthData(respBody)
                        showToastMessage("Welcome")
                        startMainActivity()
                    }

                } else {
                    val result = ApiErrorUtil.parseError(response)
                    showToastMessage(result)
                }
                binding.loading.visibility = View.GONE
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                showToastMessage("Unable to authenticate user")
                binding.loading.visibility = View.GONE
            }
        })
    }

    private fun showToastMessage(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}