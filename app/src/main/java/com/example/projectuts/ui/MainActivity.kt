package com.example.projectuts.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.projectuts.R
import com.example.projectuts.ui.about.AboutFragment
import com.example.projectuts.ui.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navDashboard: LinearLayout
    private lateinit var navProfile: LinearLayout

    private lateinit var iconDashboard: TextView
    private lateinit var textDashboard: TextView

    private lateinit var iconProfile: TextView
    private lateinit var textProfile: TextView

    private val purple: Int by lazy {
        ContextCompat.getColor(this, R.color.purple_primary)
    }

    private val gray: Int by lazy {
        ContextCompat.getColor(this, R.color.text_secondary)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor =
            ContextCompat.getColor(this, R.color.purple_dark)

        // Menghubungkan komponen navigasi
        navDashboard = findViewById(R.id.navDashboard)
        navProfile = findViewById(R.id.navProfile)

        iconDashboard = findViewById(R.id.iconDashboard)
        textDashboard = findViewById(R.id.textDashboard)

        iconProfile = findViewById(R.id.iconProfile)
        textProfile = findViewById(R.id.textProfile)

        // Menampilkan Dashboard saat aplikasi dibuka
        if (savedInstanceState == null) {
            showFragment(DashboardFragment())
        }

        setDashboardSelected()

        // Tombol Dashboard
        navDashboard.setOnClickListener {
            showFragment(DashboardFragment())
            setDashboardSelected()
        }

        // Tombol Profile
        navProfile.setOnClickListener {
            showFragment(AboutFragment())
            setProfileSelected()
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun setDashboardSelected() {
        iconDashboard.setTextColor(purple)
        textDashboard.setTextColor(purple)

        iconProfile.setTextColor(gray)
        textProfile.setTextColor(gray)

        textDashboard.textSize = 12f
        textProfile.textSize = 12f
    }

    private fun setProfileSelected() {
        iconDashboard.setTextColor(gray)
        textDashboard.setTextColor(gray)

        iconProfile.setTextColor(purple)
        textProfile.setTextColor(purple)

        textDashboard.textSize = 12f
        textProfile.textSize = 12f
    }
}
