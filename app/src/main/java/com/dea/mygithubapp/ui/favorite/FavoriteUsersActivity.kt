package com.dea.mygithubapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.ActivityFavoriteUsersBinding
import com.dea.mygithubapp.ui.ListUserAdapter
import com.dea.mygithubapp.ui.detail.DetailUserActivity
import com.dea.mygithubapp.ui.detail.FollowersFragment
import com.dea.mygithubapp.ui.detail.FollowingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteUsersActivity : AppCompatActivity() {

    private val favoriteUsersViewModel: FavoriteUsersViewModel by viewModels()
    private lateinit var adapter: ListUserAdapter
    private lateinit var binding: ActivityFavoriteUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter()
        binding.rvUserFavorite.setHasFixedSize(true)
        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showRecyclerList() {
        binding.rvUserFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvUserFavorite.adapter = adapter

        favoriteUsersViewModel.getFavoriteUsers().observe(this) {
            if (it.isNotEmpty()) {
                adapter.setData(it)
            } else {
                binding.tvWarning.visibility = View.VISIBLE
                binding.rvUserFavorite.visibility = View.INVISIBLE
            }
        }

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersResponseItem) {
                openUsersDetailActivity(data)
            }
        })
    }

    private fun openUsersDetailActivity(data: UsersResponseItem) {
        FollowersFragment.EXTRA_URL = data.followersUrl
        FollowingFragment.EXTRA_URL = data.login
        val intentParcelize = Intent(this, DetailUserActivity::class.java)
        intentParcelize.putExtra(DetailUserActivity.EXTRA_USER, data)
        startActivity(intentParcelize)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}