package com.example.expandablebottomnavigationbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.expandablebottomnavigationbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation = binding.bottomNavigationView

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController

        // You can use simply setupWithNavController like material bottom navigation bar.
        bottomNavigation.setupWithNavController(navController)

        // or you can customize the navigation with custom NavOptions.
//        bottomNavigation.setupCustomNavigationWithOptions(
//            navController,
//            NavOptions
//                .Builder()
//                .apply {
//                    // Your options here
//                }
//                .build()
//        )
    }
}