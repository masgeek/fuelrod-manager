package com.munywele.fuelrod.adapter

import android.icu.text.DecimalFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munywele.fuelrod.databinding.UserItemRowBinding
import com.munywele.fuelrod.rest.response.UserContent
import com.squareup.picasso.Picasso

class UserAdapter(private val userContentList: List<UserContent>) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private lateinit var mLayoutInflater: LayoutInflater
    private val df = DecimalFormat("#.##")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        if (!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }
        val view = UserItemRowBinding.inflate(mLayoutInflater, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val userContent = userContentList[position]
        holder.bind(userContent)
    }

    override fun getItemCount(): Int {
        return userContentList.size
    }

    inner class UserHolder(binding: UserItemRowBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private var _view: View = binding.root

        private val fullName = binding.userFullName
        private val userName = binding.userName
        private val userImage = binding.userImage
        private val creditLeft = binding.creditLeft

        init {
            binding.root.setOnClickListener(this@UserHolder)
        }

        override fun onClick(p0: View?) {
            Log.i("UserRecyclerView", "CLICK!")
        }

        fun bind(userContent: UserContent) {
            fullName.text = userContent.user.name
            userName.text = userContent.user.username
            creditLeft.text =
                String.format("%,.2f CREDITS", userContent.user.creditInfo.creditLeft)

            Picasso.get().load(userContent.user.img).into(userImage)
        }

    }
}
