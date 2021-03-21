package com.dea.mygithubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dea.mygithubapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        supportActionBar?.title = user.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvUsername.text = StringBuilder("@${user.username}")
        binding.tvNamaUser.text = user.name
        binding.tvCompany.text = user.company
        binding.tvRepository.text = StringBuilder("Total repository: ${user.repository}")
        binding.tvFollowersUser.text = user.followers.toString()
        binding.tvFollowingUser.text = user.following.toString()
        binding.tvLocation.text = user.location
        binding.circleImageView.setImageResource(user.avatar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}