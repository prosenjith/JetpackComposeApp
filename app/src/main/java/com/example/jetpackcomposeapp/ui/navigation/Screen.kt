package com.example.jetpackcomposeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Details : Screen("details/{item}") {
        fun createRoute(item: String) = "details/$item"
    }
}