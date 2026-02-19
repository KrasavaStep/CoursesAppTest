package com.example.coursesapp.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coursesapp.R
import com.example.coursesapp.databinding.ActivityMainBinding
import com.example.coursesapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.view.get
import androidx.core.view.size

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_login || destination.id == R.id.navigation_details) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        navView.setOnItemSelectedListener { menuItem ->
            resetAllIcons(navView)
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }
                R.id.navigation_bookmark -> {
                    navController.navigate(R.id.navigation_bookmark)
                    true
                }
                else -> false
            }
        }
    }

    private fun resetAllIcons(bottomNavigation: BottomNavigationView) {
        for (i in 0 until bottomNavigation.menu.size) {
            bottomNavigation.menu[i].isChecked = false
        }
    }

}