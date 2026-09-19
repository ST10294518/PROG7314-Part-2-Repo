package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.winx.app.screens.LoginScreen
import com.winx.app.screens.WelcomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // Welcome screen
        composable("welcome") {
            WelcomeScreen(
                onBeginClick = {
                    navController.navigate("login")
                }
            )
        }

        // Login screen
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

        // Register screen - coming next
        composable("register") {
            // Register screen will be added here
        }

        // Dashboard - coming later
        composable("dashboard") {
            // Dashboard screen will be added later
        }

        // Entry screen - coming later
        composable("entry") {
            // Entry screen will be added later
        }
    }
}