package com.munywele.fuelrod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.munywele.fuelrod.R
import com.munywele.fuelrod.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
