package com.tappy.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.tappy.app.LoginScreen
import com.tappy.app.ResultScreen
import com.tappy.app.WelcomeScreen
import com.tappy.app.QuizScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }

        composable(
            route = "quiz",
            deepLinks = listOf(navDeepLink { uriPattern = "tappy://quiz" })
        ) {
            QuizScreen(navController)
        }

        composable(
            route = "welcome",
            deepLinks = listOf(navDeepLink { uriPattern = "tappy://welcome" })
        ) {
            WelcomeScreen(navController)
        }

        composable(
            route = "result/{score}",
            deepLinks = listOf(navDeepLink { uriPattern = "tappy://result/{score}" })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            ResultScreen(score)
        }
    }
}