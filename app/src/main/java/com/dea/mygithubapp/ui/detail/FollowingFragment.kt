package com.dea.mygithubapp.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.FragmentFollowingBinding
import com.dea.mygithubapp.ui.ListUserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var adapter: ListUserAdapter
    private val detailUserViewModel: DetailUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()
        binding.rvList.setHasFixedSize(true)
        showRecyclerList()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = adapter

        detailUserViewModel.onFetchFollowing(EXTRA_URL)
        detailUserViewModel.listUsersFollowing.observe(requireActivity()) {
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
        FollowersFragment.EXTRA_URL = data.followersUrl
        EXTRA_URL = data.login
        val intentParcelize = Intent(requireContext(), DetailUserActivity::class.java)
        intentParcelize.putExtra(DetailUserActivity.EXTRA_USER, data)
        startActivity(intentParcelize)
    }

    companion object {
        var EXTRA_URL = "extra_url"
    }
}