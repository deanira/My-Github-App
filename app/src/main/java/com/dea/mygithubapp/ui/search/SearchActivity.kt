package com.dea.mygithubapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mygithubapp.R
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.ActivitySearchBinding
import com.dea.mygithubapp.ui.SearchResultAdapter
import com.dea.mygithubapp.ui.detail.DetailUserActivity
import com.dea.mygithubapp.ui.detail.FollowersFragment
import com.dea.mygithubapp.ui.detail.FollowingFragment
import com.dea.mygithubapp.utill.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: SearchResultAdapter
    private val searchActivityViewModel: SearchActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.cari_pengguna)
        adapter = SearchResultAdapter()
        adapter.notifyDataSetChanged()
        binding.rvList.setHasFixedSize(true)

        if (savedInstanceState != null) {
            //binding.searchDescription.visibility = View.GONE
            val listUser = savedInstanceState.getParcelableArrayList<UsersResponseItem>(EXTRA_STATE)
            if (listUser != null) {
                adapter.mData = listUser
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.mData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.background = ContextCompat.getDrawable(this, R.drawable.shape_searchview)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showRecyclerList(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                } else {
                    binding.searchDescription.visibility = View.GONE
                    showRecyclerList(newText)
                    showLoading(true)
                }
                return true
            }
        })

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.onActionViewExpanded()
        searchView.requestFocus()
        searchView.setIconifiedByDefault(false)

        return super.onPrepareOptionsMenu(menu)
    }

    private fun showRecyclerList(newText: String?) {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        searchActivityViewModel.onSearchuser("${Constant.BASE_URL}search/users?q=$newText")
        searchActivityViewModel.listUserSearchResult.observe(this) {
            if (it.isNotEmpty()) {
                binding.tvNotFound.visibility = View.INVISIBLE
                binding.rvList.visibility = View.VISIBLE
                adapter.setData(it)
                showLoading(false)
            } else {
                binding.rvList.visibility = View.INVISIBLE
                binding.tvNotFound.visibility = View.VISIBLE
                showLoading(false)
            }
        }

        adapter.setOnItemClickCallback(object : SearchResultAdapter.OnItemClickCallback {
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val EXTRA_STATE = "extra_state"
    }
}