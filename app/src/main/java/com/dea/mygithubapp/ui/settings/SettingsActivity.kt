package com.dea.mygithubapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.dea.mygithubapp.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager.commit {
            add(
                R.id.setting_holder,
                MyPreferenceFragment(),
                MyPreferenceFragment::class.java.simpleName
            )
        }
    }
}