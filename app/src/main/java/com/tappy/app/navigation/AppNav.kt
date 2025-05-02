package com.tappy.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.tappy.app.*
import com.tappy.app.CategorySelectionScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController)
        }

        composable(
            route = "welcome",
            deepLinks = listOf(navDeepLink { uriPattern = "tappy://welcome" })
        ) {
            WelcomeScreen(navController)
        }

        composable(
            route = "quiz", // из интернета
            deepLinks = listOf(navDeepLink { uriPattern = "tappy://quiz" })
        ) {
            QuizScreen(navController)
        }

        composable(
            route = "categories"
        ) {
            CategorySelectionScreen(navController)
        }

        composable(
            route = "quiz/{category}"
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            QuizScreen(navController, category = category)
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