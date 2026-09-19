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
import com.winx.app.screens.EntryDetailsScreen
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
    // CURRENT ENTRY BEING VIEWED
    // -------------------------------------------------------------

    var selectedEntry by remember {
        mutableStateOf<TravelEntry?>(null)
    }


    // -------------------------------------------------------------
    // NAVIGATION HOST
    // -------------------------------------------------------------

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {


        // =========================================================
        // WELCOME
        // =========================================================

        composable("welcome") {

            WelcomeScreen(
                onBeginClick = {
                    navController.navigate("login")
                }
            )
        }


        // =========================================================
        // LOGIN
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
        // REGISTER
        // =========================================================

        composable("register") {

            RegisterScreen(

                onCreateAccountClick = {
                    navController.navigate("dashboard")
                }
            )
        }


        // =========================================================
        // DASHBOARD
        // =========================================================

        composable("dashboard") {

            DashboardScreen(

                onAddEntryClick = {
                    navController.navigate("entry")
                },

                onAddCountryClick = {
                    navController.navigate("countries")
                },

                onEntryClick = {
                    navController.navigate("entries")
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


        // =========================================================
        // MY ENTRIES
        // =========================================================

        composable("entries") {

            EntriesScreen(

                entries = entries,

                onAddEntryClick = {
                    navController.navigate("entry")
                },

                onEntryClick = { entry ->

                    // Store the entry that the user selected.
                    selectedEntry = entry

                    // Open the details screen.
                    navController.navigate("entryDetails")
                }
            )
        }


        // =========================================================
        // ENTRY DETAILS
        // =========================================================

        composable("entryDetails") {

            selectedEntry?.let { entry ->

                EntryDetailsScreen(

                    entry = entry,

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }


        // =========================================================
        // CREATE ENTRY
        // =========================================================

        composable("entry") {

            EntryScreen(

                selectedCountry = selectedCountry,

                onPicturesClick = {
                    // Picture picker is handled inside EntryScreen.
                },

                onVideosClick = {
                    // Video picker is handled inside EntryScreen.
                },

                onCountriesClick = {
                    navController.navigate("countries")
                },

                onSaveClick = { newEntry ->

                    // Add the newly created entry to the list.
                    entries = entries + newEntry

                    // Open My Entries.
                    navController.navigate("entries") {

                        // Remove the Entry screen from
                        // the navigation back stack.
                        popUpTo("entry") {
                            inclusive = true
                        }
                    }
                },

                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }


        // =========================================================
        // COUNTRIES
        // =========================================================

        composable("countries") {

            CountriesScreen(

                onSaveClick = { country ->

                    selectedCountry = country

                    navController.popBackStack()
                },

                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}