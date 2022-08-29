package com.munywele.fuelrod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.munywele.fuelrod.databinding.ActivityMainBinding
import com.munywele.fuelrod.rest.FuelrodApiHelper
import com.munywele.fuelrod.rest.interfaces.AuthApi
import com.munywele.fuelrod.rest.request.AuthRequest
import com.munywele.fuelrod.rest.response.AuthResponse
import com.munywele.fuelrod.utils.MySharedPrefs
import com.munywele.fuelrod.utils.ApiErrorUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var prefs: MySharedPrefs
    private lateinit var authApi: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prefs = MySharedPrefs(this@MainActivity)
        setContentView(binding.root)
    }
}