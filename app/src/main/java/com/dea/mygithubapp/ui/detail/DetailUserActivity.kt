package com.dea.mygithubapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dea.mygithubapp.R
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.ActivityDetailUserBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {

    private val detailUserViewModel: DetailUserViewModel by viewModels()
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shimmerViewContainer.startShimmerAnimation()

        initAdapter()
        val user = initViewModel()
        observeData(user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observeData(user: UsersResponseItem) {
        detailUserViewModel.userDetail.observe(this, {
            supportActionBar?.title = it?.name

            with(binding) {
                tvUsername.text = StringBuilder("@${user.login}")
                tvNamaUser.text = it?.name
                tvCompany.text = it?.company
                tvRepository.text = StringBuilder("Total repository: ${it?.publicRepos}")
                tvFollowersUser.text = it?.followers.toString()
                tvFollowingUser.text = it?.following.toString()
                tvLocation.text = it?.location
                Glide.with(this@DetailUserActivity)
                    .load(it?.avatarUrl)
                    .into(binding.circleImageView)
                binding.tvCompany.visibility = View.VISIBLE
                binding.tvLocation.visibility = View.VISIBLE
            }
            showLoading(false)
        })
    }

    private fun initViewModel(): UsersResponseItem {
        val user: UsersResponseItem =
            intent.getParcelableExtra<UsersResponseItem>(EXTRA_USER) as UsersResponseItem

        detailUserViewModel.getUserDetail(user.url)
        return user
    }

    private fun initAdapter() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem = 0
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val views = arrayListOf<View>()
                tab?.view?.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                views.forEach { view ->
                    if (view is TextView) {
                        TextViewCompat.setTextAppearance(view, R.style.TabLayoutTextStyle_Selected)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val views = arrayListOf<View>()
                tab?.view?.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                views.forEach { view ->
                    if (view is TextView) {
                        TextViewCompat.setTextAppearance(
                            view,
                            R.style.TabLayoutTextStyle_Unselected
                        )
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val views = arrayListOf<View>()
                tab?.view?.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                views.forEach { view ->
                    if (view is TextView) {
                        TextViewCompat.setTextAppearance(view, R.style.TabLayoutTextStyle_Selected)
                    }
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.shimmerViewContainer.visibility = View.VISIBLE
        } else {
            binding.shimmerViewContainer.stopShimmerAnimation()
            binding.shimmerViewContainer.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_followers,
            R.string.tab_text_following
        )
    }

}