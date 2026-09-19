package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            // Login screen will be added here
        }

        composable("register") {
            // Register screen will be added here
        }

        composable("dashboard") {
            // Dashboard screen will be added here
        }

        composable("entry") {
            // Entry screen will be added here
        }
    }
}