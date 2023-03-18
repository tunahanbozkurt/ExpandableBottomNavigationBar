package com.example.expandablebottomnavigationbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expandable_bottom_navigation_bar.ExpandableBottomNavigationBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation =
            findViewById<ExpandableBottomNavigationBar>(R.id.bottom_navigation_view)

        bottomNavigation.setOnItemSelectedListener {
            when (it) {
                R.id.ebnb_home -> {
                    println("home")
                }
                R.id.ebnb_search -> {
                    println("search")
                }
                R.id.ebnb_download -> {
                    println("download")
                }
                R.id.ebnb_profile -> {
                    println("profile")
                }
            }
        }
        bottomNavigation.setItemSelected(id = R.id.ebnb_home)
    }
}