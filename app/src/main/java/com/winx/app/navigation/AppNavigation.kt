package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.winx.app.screens.LoginScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                onLoginClick = {
                    // Dashboard navigation will be connected later
                },
                onCreateAccountClick = {
                    // Register navigation will be connected later
                }
            )
        }

        composable("register") {
            // Register screen will be added next
        }

        composable("dashboard") {
            // Dashboard screen will be added later
        }

        composable("entry") {
            // Entry screen will be added later
        }
    }
}