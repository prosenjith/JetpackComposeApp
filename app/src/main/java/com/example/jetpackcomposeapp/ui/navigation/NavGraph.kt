package com.example.jetpackcomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposeapp.ui.screens.DetailsScreen
import com.example.jetpackcomposeapp.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Details.route) { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item")
            DetailsScreen(item)
        }
    }
}