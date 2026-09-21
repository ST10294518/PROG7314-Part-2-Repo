package com.winx.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.winx.app.auth.GoogleAuthManager
import com.winx.app.screens.AboutUsScreen
import com.winx.app.screens.CalendarScreen
import com.winx.app.screens.CountriesScreen
import com.winx.app.screens.DashboardScreen
import com.winx.app.screens.EntriesScreen
import com.winx.app.screens.EntryDetailsScreen
import com.winx.app.screens.EntryScreen
import com.winx.app.screens.LanguageScreen
import com.winx.app.screens.LibraryScreen
import com.winx.app.screens.LoginScreen
import com.winx.app.screens.NotificationsScreen
import com.winx.app.screens.ProfileScreen
import com.winx.app.screens.RecentlyDeletedScreen
import com.winx.app.screens.RegisterScreen
import com.winx.app.screens.SecurityScreen
import com.winx.app.screens.SettingsScreen
import com.winx.app.screens.TravelEntry
import com.winx.app.screens.WelcomeScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // -------------------------------------------------------------
    // GOOGLE AUTHENTICATION
    // -------------------------------------------------------------

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val googleAuthManager = remember(context) {
        GoogleAuthManager(context)
    }

    var googleSignInLoading by remember {
        mutableStateOf(false)
    }

    var googleSignInError by remember {
        mutableStateOf<String?>(null)
    }

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
    // CURRENT ENTRY
    // -------------------------------------------------------------

    var selectedEntry by remember {
        mutableStateOf<TravelEntry?>(null)
    }

    // -------------------------------------------------------------
    // NAVIGATION
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

                    navController.navigate("dashboard") {

                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onCreateAccountClick = {
                    navController.navigate("register")
                },

                onGoogleSignInClick = {

                    if (!googleSignInLoading) {

                        googleSignInLoading = true
                        googleSignInError = null

                        coroutineScope.launch {

                            val result =
                                googleAuthManager.signInWithGoogle()

                            result
                                .onSuccess {

                                    googleSignInLoading = false

                                    navController.navigate("dashboard") {

                                        popUpTo("login") {
                                            inclusive = true
                                        }
                                    }
                                }
                                .onFailure { exception ->

                                    googleSignInLoading = false

                                    googleSignInError =
                                        exception.message
                                            ?: "Google sign-in failed."
                                }
                        }
                    }
                },

                googleSignInLoading = googleSignInLoading,

                googleSignInError = googleSignInError
            )
        }

        // =========================================================
        // REGISTER
        // =========================================================

        composable("register") {

            RegisterScreen(

                onCreateAccountClick = {

                    navController.navigate("login") {

                        popUpTo("register") {
                            inclusive = true
                        }
                    }
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

                    navController.navigate("calendar") {
                        launchSingleTop = true
                    }
                },

                onLibraryClick = {

                    navController.navigate("library") {
                        launchSingleTop = true
                    }
                },

                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        // =========================================================
        // SETTINGS
        // =========================================================

        composable("settings") {

            SettingsScreen(

                onBack = {
                    navController.popBackStack()
                },

                onProfileClick = {
                    navController.navigate("profile")
                },

                onLanguageClick = {
                    navController.navigate("language")
                },

                onRecentlyDeletedClick = {
                    navController.navigate("recentlyDeleted")
                },

                onSecurityClick = {
                    navController.navigate("security")
                },

                onNotificationsClick = {
                    navController.navigate("notifications")
                },

                onAboutUsClick = {
                    navController.navigate("aboutUs")
                },

                onLogout = {

                    googleAuthManager.signOut()

                    navController.navigate("welcome") {

                        popUpTo("settings") {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        // =========================================================
        // PROFILE
        // =========================================================

        composable("profile") {

            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================================================
        // LANGUAGE
        // =========================================================

        composable("language") {

            LanguageScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================================================
        // RECENTLY DELETED
        // =========================================================

        composable("recentlyDeleted") {

            RecentlyDeletedScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================================================
        // SECURITY
        // =========================================================

        composable("security") {

            SecurityScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================================================
        // NOTIFICATIONS
        // =========================================================

        composable("notifications") {

            NotificationsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================================================
        // ABOUT US
        // =========================================================

        composable("aboutUs") {

            AboutUsScreen(
                onBack = {
                    navController.popBackStack()
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

                    selectedEntry = entry

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
                    // Picture picker handled inside EntryScreen.
                },

                onVideosClick = {
                    // Video picker handled inside EntryScreen.
                },

                onCountriesClick = {
                    navController.navigate("countries")
                },

                onSaveClick = { newEntry ->

                    entries = entries + newEntry

                    navController.navigate("entries") {

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

        // =========================================================
        // CALENDAR
        // =========================================================

        composable("calendar") {

            CalendarScreen(

                onDashboardClick = {

                    navController.navigate("dashboard") {

                        popUpTo("dashboard") {
                            inclusive = false
                        }

                        launchSingleTop = true
                    }
                },

                onEntryClick = {

                    navController.navigate("entry") {
                        launchSingleTop = true
                    }
                },

                onLibraryClick = {

                    navController.navigate("library") {
                        launchSingleTop = true
                    }
                },

                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        // =========================================================
        // LIBRARY
        // =========================================================

        composable("library") {

            LibraryScreen(

                onDashboardClick = {

                    navController.navigate("dashboard") {

                        popUpTo("dashboard") {
                            inclusive = false
                        }

                        launchSingleTop = true
                    }
                },

                onEntryClick = {

                    navController.navigate("entry") {
                        launchSingleTop = true
                    }
                },

                onCalendarClick = {

                    navController.navigate("calendar") {
                        launchSingleTop = true
                    }
                },

                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }
    }
}