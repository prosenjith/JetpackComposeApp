package com.example.jetpackcomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposeapp.ui.screens.home.HomeScreen
import com.example.jetpackcomposeapp.ui.screens.profile.ProfileScreen
import com.example.jetpackcomposeapp.ui.screens.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
    }
}
