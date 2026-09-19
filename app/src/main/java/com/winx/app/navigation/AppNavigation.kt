package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.winx.app.screens.LoginScreen
import com.winx.app.screens.WelcomeScreen
import com.winx.app.screens.RegisterScreen
import com.winx.app.screens.DashboardScreen
import com.winx.app.screens.EntryScreen
import com.winx.app.screens.CountriesScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // -------------------------------------------------------------
    // SELECTED COUNTRY
    // -------------------------------------------------------------

    var selectedCountry by remember {
        mutableStateOf("")
    }


    // -------------------------------------------------------------
    // NAVIGATION HOST
    // -------------------------------------------------------------

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // ---------------------------------------------------------
        // WELCOME SCREEN
        // ---------------------------------------------------------

        composable("welcome") {

            WelcomeScreen(
                onBeginClick = {
                    navController.navigate("login")
                }
            )
        }


        // ---------------------------------------------------------
        // LOGIN SCREEN
        // ---------------------------------------------------------

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


        // ---------------------------------------------------------
        // REGISTER SCREEN
        // ---------------------------------------------------------

        composable("register") {

            RegisterScreen(
                onCreateAccountClick = {
                    navController.navigate("dashboard")
                }
            )
        }


        // ---------------------------------------------------------
        // DASHBOARD SCREEN
        // ---------------------------------------------------------

        composable("dashboard") {

            DashboardScreen(

                onAddEntryClick = {
                    navController.navigate("entry")
                },

                onAddCountryClick = {
                    navController.navigate("countries")
                },

                onEntryClick = {
                    navController.navigate("entry")
                },

                onCalendarClick = {
                    // Calendar screen will be connected later
                },

                onCountriesClick = {
                    navController.navigate("countries")
                },

                onSettingsClick = {
                    // Settings screen will be connected later
                }
            )
        }


        // ---------------------------------------------------------
        // ENTRY SCREEN
        // ---------------------------------------------------------

        composable("entry") {

            EntryScreen(

                // Selected country is passed into EntryScreen
                selectedCountry = selectedCountry,

                onPicturesClick = {
                    // Picture picker is handled inside EntryScreen
                },

                onVideosClick = {
                    // Video picker is handled inside EntryScreen
                },

                onCountriesClick = {
                    navController.navigate("countries")
                },

                onSaveClick = {
                    navController.popBackStack()
                },

                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }


        // ---------------------------------------------------------
        // COUNTRIES SCREEN
        // ---------------------------------------------------------

        composable("countries") {

            CountriesScreen(

                // Save the selected country
                onSaveClick = { country ->

                    selectedCountry = country

                    // Return to the previous screen
                    navController.popBackStack()
                },

                // Cancel and return to previous screen
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}