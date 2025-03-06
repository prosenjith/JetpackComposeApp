package com.example.jetpackcomposeapp.ui.navigation

import androidx.annotation.DrawableRes
import com.example.jetpackcomposeapp.R

sealed class BottomNavScreen(val route: String, @DrawableRes val icon: Int, val title: String) {
    data object Home : BottomNavScreen("home", R.drawable.ic_home, "Home")
    data object Profile : BottomNavScreen("profile", R.drawable.ic_profile, "Profile")
    data object Settings : BottomNavScreen("settings", R.drawable.ic_settings, "Settings")
}