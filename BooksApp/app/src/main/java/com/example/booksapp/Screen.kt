package com.example.booksapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailScreen : Screen("home/{index}") {
        fun createRoute(index: Int) = "home/$index"
    }
}