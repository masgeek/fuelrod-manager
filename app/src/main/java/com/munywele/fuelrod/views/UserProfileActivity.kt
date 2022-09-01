package com.munywele.fuelrod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.material.appbar.AppBarLayout
import com.munywele.fuelrod.R
import com.munywele.fuelrod.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        val toolBarLayout = binding.toolBarLayout
        setSupportActionBar(toolBar)

        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }

            if (scrollRange + verticalOffset == 0) {
                toolBarLayout.title = getString(R.string.profile)
                isShow = true
            }

            if (isShow) {
                toolBarLayout.title = " "
                isShow = false
            }
        })
    }
}
