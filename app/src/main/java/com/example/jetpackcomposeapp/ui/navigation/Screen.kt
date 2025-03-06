package com.example.jetpackcomposeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Details : Screen("details/{itemId}")
    data object About : Screen("about")
    data object Contact : Screen("contact")

    fun withArgs(vararg args: String): String {
        var updatedRoute = route
        args.forEach { arg ->
            updatedRoute = updatedRoute.replaceFirst("\\{[^}]+\\}".toRegex(), arg)
        }
        return updatedRoute
    }
}