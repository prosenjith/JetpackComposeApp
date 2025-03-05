package com.example.jetpackcomposeapp.ui.navigation

import androidx.annotation.DrawableRes
import com.example.jetpackcomposeapp.R

sealed class Screen(val route: String, @DrawableRes val icon: Int, val title: String) {
    data object Home : Screen("home", R.drawable.ic_home, "Home")
    data object Profile : Screen("profile", R.drawable.ic_profile, "Profile")
    data object Settings : Screen("settings", R.drawable.ic_settings, "Settings")
}