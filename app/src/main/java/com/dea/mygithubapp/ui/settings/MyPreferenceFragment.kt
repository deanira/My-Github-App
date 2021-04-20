package com.dea.mygithubapp.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dea.mygithubapp.R
import java.util.*


class MyPreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var REMINDER: String
    private lateinit var LANGUAGE: String
    private lateinit var reminderPreference: SwitchPreference
    private lateinit var languagePreference: ListPreference
    private var reminderState: Boolean = false
    private var currentLang: String = ""
    private lateinit var reminderReceiver: ReminderReceiver


    companion object {
        const val DEFAULT_VALUE_REMINDER = false
        const val repeatTime = "09:00"
        const val repeatMessage = "It's time to explore more github users"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        reminderReceiver = ReminderReceiver()
        init()
        setSummaries()
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        reminderState = sh.getBoolean(REMINDER, DEFAULT_VALUE_REMINDER)
        if (reminderState) {
            reminderPreference.summary = "Enabled"
        } else {
            reminderPreference.summary = "Disabled"
        }
        when (currentLang) {
            "en" -> languagePreference.summary = "English"
            "in" -> languagePreference.summary = "Indonesia"
        }
    }

    private fun setReminder() {
        reminderReceiver.setRepeatingAlarm(
            requireContext(), repeatTime, repeatMessage
        )
    }

    private fun init() {
        REMINDER = resources.getString(R.string.key_reminder)
        LANGUAGE = resources.getString(R.string.key_language)
        reminderPreference = findPreference<SwitchPreference>(REMINDER) as SwitchPreference
        languagePreference = findPreference<ListPreference>(LANGUAGE) as ListPreference
        currentLang = Locale.getDefault().language
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        when (key) {
            REMINDER -> {
                reminderState = sharedPreferences.getBoolean(REMINDER, DEFAULT_VALUE_REMINDER)
                if (reminderState) {
                    reminderPreference.summary = "Enabled"
                    setReminder()
                } else {
                    reminderPreference.summary = "Disabled"
                    reminderReceiver.cancelAlarm(requireContext())
                }
            }
            LANGUAGE -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
                when (Locale.getDefault().language) {
                    "en" -> languagePreference.summary = "English"
                    "in" -> languagePreference.summary = "Indonesia"
                }
            }
        }
    }

    //function mengubah bahasa ini masih belum berhasil, jika kakak code reviewer ada waktu
    //sudikah kakak code reviewer membantu agar berhasil?
    private fun changeLanguagePref(lang: String) {
        var locale: Locale? = null
        when (lang) {
            "English" -> {
                locale = Locale("en")
            }
            "Indonesia" -> {
                locale = Locale("in")
            }
        }
        Locale.setDefault(locale)
        val config = Configuration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        }
        requireContext().createConfigurationContext(config)
    }
}