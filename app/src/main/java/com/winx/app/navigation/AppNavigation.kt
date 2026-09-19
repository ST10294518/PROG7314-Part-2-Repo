package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.winx.app.screens.LoginScreen
import com.winx.app.screens.WelcomeScreen
import com.winx.app.screens.RegisterScreen
import com.winx.app.screens.DashboardScreen

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
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onCreateAccountClick = {
                    navController.navigate("dashboard")
                }
            )
        }

        composable("dashboard") {
            DashboardScreen(
                onAddEntryClick = {
                    navController.navigate("entry")
                },
                onAddCountryClick = {
                    // Countries screen will be connected later
                },
                onEntryClick = {
                    navController.navigate("entry")
                },
                onCalendarClick = {
                    // Calendar screen will be connected later
                },
                onCountriesClick = {
                    // Countries screen will be connected later
                },
                onSettingsClick = {
                    // Settings screen will be connected later
                }
            )
        }

        // Entry screen - coming later
        composable("entry") {
            // Entry screen will be added later
        }
    }
}