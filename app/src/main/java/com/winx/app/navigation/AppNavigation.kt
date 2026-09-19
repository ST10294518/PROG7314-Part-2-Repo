package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.winx.app.screens.CountriesScreen
import com.winx.app.screens.DashboardScreen
import com.winx.app.screens.EntriesScreen
import com.winx.app.screens.EntryScreen
import com.winx.app.screens.LoginScreen
import com.winx.app.screens.RegisterScreen
import com.winx.app.screens.TravelEntry
import com.winx.app.screens.WelcomeScreen


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
    // SAVED ENTRIES
    // -------------------------------------------------------------

    var entries by remember {
        mutableStateOf<List<TravelEntry>>(emptyList())
    }


    // -------------------------------------------------------------
    // NAVIGATION HOST
    // -------------------------------------------------------------

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {


        // =========================================================
        // WELCOME SCREEN
        // =========================================================

        composable("welcome") {

            WelcomeScreen(
                onBeginClick = {
                    navController.navigate("login")
                }
            )
        }


        // =========================================================
        // LOGIN SCREEN
        // =========================================================

        composable("login") {

            LoginScreen(

                onLoginClick = {
                    navController.navigate("dashboard")
                },

                onCreateAccountClick = {
                    navController.navigate("register")
                }
            )
        }


        // =========================================================
        // REGISTER SCREEN
        // =========================================================

        composable("register") {

            RegisterScreen(

                onCreateAccountClick = {
                    navController.navigate("dashboard")
                }
            )
        }


        // =========================================================
        // DASHBOARD SCREEN
        // =========================================================

        composable("dashboard") {

            DashboardScreen(

                // -------------------------------------------------
                // ADD ENTRY
                // -------------------------------------------------

                onAddEntryClick = {
                    navController.navigate("entry")
                },


                // -------------------------------------------------
                // ADD COUNTRY
                // -------------------------------------------------

                onAddCountryClick = {
                    navController.navigate("countries")
                },


                // -------------------------------------------------
                // ENTRIES
                // -------------------------------------------------

                onEntryClick = {
                    navController.navigate("entries")
                },


                // -------------------------------------------------
                // CALENDAR
                // -------------------------------------------------

                onCalendarClick = {
                    // Calendar screen will be connected later
                },


                // -------------------------------------------------
                // COUNTRIES
                // -------------------------------------------------

                onCountriesClick = {
                    navController.navigate("countries")
                },


                // -------------------------------------------------
                // SETTINGS
                // -------------------------------------------------

                onSettingsClick = {
                    // Settings screen will be connected later
                }
            )
        }


        // =========================================================
        // MY ENTRIES SCREEN
        // =========================================================

        composable("entries") {

            EntriesScreen(

                // Pass all saved entries to EntriesScreen
                entries = entries,

                // Add Entry button
                onAddEntryClick = {

                    navController.navigate("entry")
                }
            )
        }


        // =========================================================
        // ENTRY SCREEN
        // =========================================================

        composable("entry") {

            EntryScreen(

                // Pass selected country into EntryScreen
                selectedCountry = selectedCountry,


                // -------------------------------------------------
                // PICTURES
                // -------------------------------------------------

                onPicturesClick = {

                    // Picture picker is handled
                    // directly inside EntryScreen
                },


                // -------------------------------------------------
                // VIDEOS
                // -------------------------------------------------

                onVideosClick = {

                    // Video picker is handled
                    // directly inside EntryScreen
                },


                // -------------------------------------------------
                // COUNTRIES
                // -------------------------------------------------

                onCountriesClick = {

                    navController.navigate("countries")
                },


                // -------------------------------------------------
                // SAVE ENTRY
                // -------------------------------------------------

                onSaveClick = { newEntry ->

                    // Add the new entry to the saved entries list
                    entries = entries + newEntry

                    // Open My Entries after saving
                    navController.navigate("entries") {

                        // Remove the Entry screen from the
                        // back stack so the saved form isn't
                        // opened again when pressing Back.
                        popUpTo("entry") {
                            inclusive = true
                        }
                    }
                },


                // -------------------------------------------------
                // CANCEL
                // -------------------------------------------------

                onCancelClick = {

                    navController.popBackStack()
                }
            )
        }


        // =========================================================
        // COUNTRIES SCREEN
        // =========================================================

        composable("countries") {

            CountriesScreen(

                // -------------------------------------------------
                // SAVE COUNTRY
                // -------------------------------------------------

                onSaveClick = { country ->

                    // Store the selected country
                    selectedCountry = country

                    // Return to the previous screen
                    navController.popBackStack()
                },


                // -------------------------------------------------
                // CANCEL COUNTRY
                // -------------------------------------------------

                onCancelClick = {

                    navController.popBackStack()
                }
            )
        }
    }
}