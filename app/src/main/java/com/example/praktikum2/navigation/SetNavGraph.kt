package com.example.praktikum2.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.praktikum2.screen.HomeScreen
import com.example.praktikum2.screen.ResultScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Result.route,
            arguments = listOf(navArgument ("text") {
                type = NavType.StringType
            })
        ) {
            ResultScreen(it.arguments?.getString("text").toString(), navController)
        }
    }
}