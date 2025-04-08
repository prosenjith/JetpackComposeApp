package com.example.jetpackcomposeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Details : Screen("details/{itemId}")
    data object About : Screen("about")
    data object Contact : Screen("contact")
    data object User: Screen("user")
    data object Posts: Screen("posts/{userId}")
    data object Chat: Screen("chat/{userName}")
    data object Post: Screen("post")
    data object Search: Screen("search")

    fun withArgs(vararg args: String): String {
        var updatedRoute = route
        args.forEach { arg ->
            updatedRoute = updatedRoute.replaceFirst("\\{[^}]+\\}".toRegex(), arg)
        }
        return updatedRoute
    }
}