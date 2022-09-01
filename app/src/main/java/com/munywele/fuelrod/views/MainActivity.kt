package com.munywele.fuelrod.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.munywele.fuelrod.adapter.UserAdapter
import com.munywele.fuelrod.databinding.ActivityMainBinding
import com.munywele.fuelrod.rest.FuelrodApiHelper
import com.munywele.fuelrod.rest.interfaces.UserApi
import com.munywele.fuelrod.rest.response.Paginated
import com.munywele.fuelrod.rest.response.UserContent
import com.munywele.fuelrod.utils.ApiErrorUtil
import com.munywele.fuelrod.utils.MyConstants
import com.munywele.fuelrod.utils.MySharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var prefs: MySharedPrefs
    private lateinit var userApi: UserApi
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prefs = MySharedPrefs(this@MainActivity)
        setContentView(binding.root)

        mAdapter = UserAdapter(this@MainActivity)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        binding.usersRecyclerView.layoutManager = linearLayoutManager
        binding.usersRecyclerView.setHasFixedSize(true)
        binding.usersRecyclerView.adapter = mAdapter

        binding.swipeContainer.setOnRefreshListener {
            loadUserList()
        }

        loadUserList()

    }

    private fun loadUserList() {
        binding.shimmerLayout.startShimmer()
        userApi = FuelrodApiHelper.getInstance(this).create(UserApi::class.java)

        val mapper = ObjectMapper()
        userApi.users().enqueue(object : Callback<Paginated> {
            override fun onResponse(
                call: Call<Paginated>,
                response: Response<Paginated>
            ) {
                if (response.isSuccessful) {
                    val respBody = response.body()
                    if (respBody != null && respBody.content.isNotEmpty()) {
                        val userContentList = mapper.convertValue(
                            respBody.content,
                            object : TypeReference<List<UserContent>>() {})
                        mAdapter.setItems(userContentList)
                        showToastMessage("${userContentList?.size} Users loaded")
                    }
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                } else {
                    val result = ApiErrorUtil.parseError(response)
                    showToastMessage(result)
                }
                binding.swipeContainer.isRefreshing = false
            }

            override fun onFailure(call: Call<Paginated>, t: Throwable) {
                showToastMessage("Unable to load user list")
                binding.swipeContainer.isRefreshing = false
            }

        })
    }

    public override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }

    private fun showToastMessage(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onUserClicked(userContent: UserContent, position: Int) {
        val intent = Intent(this@MainActivity, UserProfileActivity::class.java)
        intent.putExtra(MyConstants.USER_CONTENT, userContent)
        startActivity(intent)
        Animatoo.animateSwipeLeft(this@MainActivity)
    }
}
