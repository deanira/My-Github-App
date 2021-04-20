package com.dea.mygithubapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dea.mygithubapp.R
import com.dea.mygithubapp.ui.mainActivity.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val ivGithubMark: ImageView = findViewById(R.id.iv_github_mark)

        ivGithubMark.alpha = 0f
        ivGithubMark.animate().setDuration(3000).alpha(1f).withEndAction {
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}