package com.example.booksapp

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { index ->
                        navController.navigate(Screen.DetailScreen.createRoute(index))
                        Log.d("Hm", "Ke detail dgn membawa $index")
                    })
            }

            composable(
                Screen.DetailScreen.route,
                listOf(navArgument("index") { type = NavType.IntType })
            ) {

                val index = it.arguments?.getInt("index") ?: 0
                DetailScreen(
                    itemIndex = index,
                    navigateBack = { navController.navigateUp() },
//                        navigateToDetail = {
//                            navController.navigate("detail")
//                        }
                )

//                DetailScreen(
//                    rewardId = id,
//                    navigateToCart = {
//                        navController.popBackStack()
//                        navController.navigate(Screen.Cart.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
            }
        }
    }
}
