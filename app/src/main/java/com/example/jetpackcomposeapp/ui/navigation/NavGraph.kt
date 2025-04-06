package com.example.jetpackcomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackcomposeapp.asynchronous.PostScreen
import com.example.jetpackcomposeapp.graphqlpractice.ui.PostsScreen
import com.example.jetpackcomposeapp.graphqlpractice.ui.UserScreen
import com.example.jetpackcomposeapp.ui.screens.about.AboutScreen
import com.example.jetpackcomposeapp.ui.screens.contact.ContactScreen
import com.example.jetpackcomposeapp.ui.screens.details.DetailsScreen
import com.example.jetpackcomposeapp.ui.screens.home.HomeScreen
import com.example.jetpackcomposeapp.ui.screens.profile.ProfileScreen
import com.example.jetpackcomposeapp.ui.screens.settings.SettingsScreen
import com.example.jetpackcomposeapp.websocketpractice.ChatScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavScreen.Home.route) {
        composable(BottomNavScreen.Home.route) { HomeScreen(navController) }
        composable(BottomNavScreen.Profile.route) { ProfileScreen(navController) }
        composable(BottomNavScreen.Settings.route) { SettingsScreen(navController) }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            DetailsScreen(navController, itemId)
        }
        composable(Screen.About.route) { AboutScreen(navController) }
        composable(Screen.Contact.route) { ContactScreen(navController) }
        composable(Screen.User.route) {
            UserScreen { userId ->
                navController.navigate(Screen.Posts.withArgs(userId))
            }
        }
        composable(
            Screen.Posts.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            PostsScreen(userId)
        }
        composable(
            Screen.Chat.route,
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            ChatScreen(userName)
        }
        composable(Screen.Post.route) { PostScreen() }
    }
}
