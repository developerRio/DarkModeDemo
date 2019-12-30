package com.originalstocks.darkmodedemo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var toggleButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appSettings: SharedPreferences = getSharedPreferences("darkModePrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettings.edit()
        val isDarkModeOn: Boolean = appSettings.getBoolean("darkMode", false)

        toggleButton = findViewById(R.id.dark_mode_toggle_button)

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            toggleButton?.text = "Disable Dark Mode"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            toggleButton?.text = "Enable Dark Mode"
        }

        toggleButton?.setOnClickListener {
            if (isDarkModeOn) {
                toggleButton?.text = "Enable Dark Mode"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("darkMode", false)
                sharedPrefsEdit.apply()
                recreate()
            } else {
                toggleButton?.text = "Disable Dark Mode"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("darkMode", true)
                sharedPrefsEdit.apply()
                recreate()
            }
        }

    }



    object ThemeSelector {

        /*to use this function just call it like
        *
        *         ThemeSelector.applyTheme(" YOUR THEME TYPE FROM BELOW CONST ")
        * */

        private const val lightMode = "light"
        private const val darkMode = "dark"
        private const val batterySaverMode = "battery"
        const val default = "default"

        fun applyTheme(theme: String) {
            when (theme) {
                lightMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                darkMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                batterySaverMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            }
        }
    }
}
