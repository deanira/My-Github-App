package com.dea.consumerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.consumerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val favoriteUsersViewModel: FavoriteUsersViewModel by viewModels()
    private lateinit var adapter: ListUserAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter()

        binding.rvUserFavorite.setHasFixedSize(true)

        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showRecyclerList() {
        binding.rvUserFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvUserFavorite.adapter = adapter

        favoriteUsersViewModel.setFavorite(this)

        favoriteUsersViewModel.getFavoriteUsers().observe(this) {
            if (it != null) {
                adapter.setData(it)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}