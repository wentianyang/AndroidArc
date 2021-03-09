package com.android.network.environment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.android.network.R

private const val TITLE_TAG = "settingsActivityTitle"

class EnvironmentSettingsActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private var _currentNetworkEnvironment = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, HeaderFragment())
                .commit()
        } else {
            title = savedInstanceState.getCharSequence(TITLE_TAG)
        }
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                setTitle(R.string.title_activity_environment_settings)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _currentNetworkEnvironment = PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean(NETWORK_PREFERENCE_PREF_KEY, true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, title)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.popBackStackImmediate()) {
            return true
        }
        return super.onSupportNavigateUp()
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment
        ).apply {
            arguments = args
            setTargetFragment(caller, 0)
        }
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, fragment)
            .addToBackStack(null)
            .commit()
        title = pref.title
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val newValue = PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean(NETWORK_PREFERENCE_PREF_KEY, true)
        if (newValue != _currentNetworkEnvironment) {
            android.os.Process.killProcess(android.os.Process.myPid())
        } else {
            finish()
        }
    }

    class HeaderFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey)
            val networkEnvironment = findPreference<SwitchPreferenceCompat>("network_environment")
            networkEnvironment?.setOnPreferenceChangeListener { preference, newValue ->
                preference.sharedPreferences.edit()
                    .putBoolean(NETWORK_PREFERENCE_PREF_KEY, newValue as Boolean)
                    .apply()
                true
            }
        }
    }

    companion object {
        private const val NETWORK_PREFERENCE_PREF_KEY = "network_preference_pref_key"

        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, EnvironmentSettingsActivity::class.java))
        }

        /**
         * 是否是开发环境
         */
        @JvmStatic
        fun isDevEnvironment(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
                NETWORK_PREFERENCE_PREF_KEY, true
            )
        }
    }
}