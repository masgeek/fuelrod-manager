package com.munywele.fuelrod.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.munywele.fuelrod.databinding.ActivityUserProfileBinding
import com.munywele.fuelrod.rest.response.UserContent
import com.munywele.fuelrod.utils.MyConstants


class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding

    private var clientName: String? = null
    private var userContent: UserContent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);


        if (intent.extras != null) {
            userContent = intent.getParcelableExtra(MyConstants.USER_CONTENT)
            clientName = userContent!!.user.fullName
        }

        var scrollRange = -1
        var isShown = true
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }

            if (scrollRange + verticalOffset == 0) {
                supportActionBar?.title = clientName
                isShown = true
            } else  {
                supportActionBar?.title = " "
                isShown = false
            }
        }

//        with(binding) {
//            userNameTextDisplay.text = userContent?.user?.username
//            nameTextDisplay.text = userContent?.user?.fullName
//        }
//
//        with(binding.profileContent) {
//            nameTextDisplay.text = userContent?.user?.fullName
//            userNameTextDisplay.text = userContent?.user?.username
//            emailTextDisplay.text = userContent?.user?.email
//        }

    }
}
