package com.munywele.fuelrod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munywele.fuelrod.databinding.UserItemRowBinding
import com.munywele.fuelrod.rest.response.UserContent
import com.squareup.picasso.Picasso

class UserAdapter(private val mOnUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userContentList: List<UserContent> = emptyList()
    private lateinit var mLayoutInflater: LayoutInflater

    interface OnUserClickListener {
        fun onUserClicked(userContent: UserContent, position: Int)
    }

    fun setItems(items: List<UserContent>) {
        this.userContentList = items
        notifyItemRangeChanged(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        if (!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }
        val view = UserItemRowBinding.inflate(mLayoutInflater, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val userContent = userContentList[position]
        holder.bind(userContent, position)
    }

    override fun getItemCount(): Int {
        return userContentList.size
    }


    inner class UserHolder(binding: UserItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        private val fullName = binding.userFullName
        private val userName = binding.userName
        private val userImage = binding.userImage
        private val creditLeft = binding.creditLeft
        private val cardView = binding.userCard


        fun bind(userContent: UserContent, position: Int) {
            fullName.text = userContent.user.fullName
            userName.text = userContent.user.username
            userName.text = userContent.user.username
            creditLeft.text =
                String.format("%,.2f CREDITS", userContent.user.creditInfo.creditLeft)

            Picasso.get().load(userContent.user.img).into(userImage)

            cardView.setOnClickListener {
                mOnUserClickListener.onUserClicked(userContent, position)
            }
        }

    }
}
