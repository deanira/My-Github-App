package com.dea.mygithubapp.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mygithubapp.R
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.ActivityMainBinding
import com.dea.mygithubapp.ui.ListUserAdapter
import com.dea.mygithubapp.ui.detail.DetailUserActivity
import com.dea.mygithubapp.ui.detail.FollowersFragment.Companion.EXTRA_URL
import com.dea.mygithubapp.ui.detail.FollowingFragment
import com.dea.mygithubapp.ui.favorite.FavoriteUsersActivity
import com.dea.mygithubapp.ui.search.SearchActivity
import com.dea.mygithubapp.ui.settings.ReminderReceiver
import com.dea.mygithubapp.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListUserAdapter
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var reminderReceiver: ReminderReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reminderReceiver = ReminderReceiver()

        supportActionBar?.title = getString(R.string.find_people)

        adapter = ListUserAdapter()

        binding.rvList.setHasFixedSize(true)

        showRecyclerList()

        binding.tvSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        mainActivityViewModel.onFetchUsers()
        mainActivityViewModel.listUser.observe(this) {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        }

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersResponseItem) {
                openUsersDetailActivity(data)
            }
        })
    }

    private fun openUsersDetailActivity(data: UsersResponseItem) {
        EXTRA_URL = data.followersUrl
        FollowingFragment.EXTRA_URL = data.login
        val intentParcelize = Intent(this, DetailUserActivity::class.java)
        intentParcelize.putExtra(DetailUserActivity.EXTRA_USER, data)
        startActivity(intentParcelize)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.action_fav) {
            val intent = Intent(this, FavoriteUsersActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}