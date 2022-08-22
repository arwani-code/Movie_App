package com.moviesplash.app.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.moviesplash.app.HomeActivity
import com.moviesplash.app.R
import com.moviesplash.app.core.utils.DarkMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_setting)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.title = getString(R.string.setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            updateThemeBased()
        }

        private fun updateThemeBased() {
            val listPreference = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            listPreference?.setOnPreferenceChangeListener { _, newValue ->
                when (newValue) {
                    getString(R.string.pref_dark_on) -> {
                        val mode = DarkMode.ON.value
                        updateTheme(mode)
                    }
                    getString(R.string.pref_dark_off) -> {
                        val mode = DarkMode.OFF.value
                        updateTheme(mode)
                    }
                    getString(R.string.pref_dark_follow_system) -> {
                        val mode = DarkMode.FOLLOW_SYSTEM.value
                        updateTheme(mode)
                    }
                }
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                true
            }
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
}