package com.example.expandablebottomnavigationbar.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions

fun Fragment.setCustomBackNavigation(navController: NavController) {
    this.requireActivity().onBackPressedDispatcher.addCallback(this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.navigate(
                    navController.graph.findStartDestination().id,
                    args = null,
                    navOptions = NavOptions.Builder().apply {
                        setLaunchSingleTop(true)
                        setRestoreState(true)
                        setPopUpTo(
                            navController.graph.findStartDestination().id,
                            inclusive = false,
                            saveState = true
                        )
                    }.build()
                )
            }
        })
}