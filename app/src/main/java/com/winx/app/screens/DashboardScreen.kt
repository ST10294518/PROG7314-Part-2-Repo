package com.winx.app.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.winx.app.utils.T
import com.winx.app.utils.winxGreeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.winx.app.ui.theme.WinxBlue
import com.winx.app.ui.theme.WinxDarkBlue
import com.winx.app.ui.theme.WinxLightPink
import com.winx.app.ui.theme.WinxOrange
import com.winx.app.ui.theme.WinxPink
import com.winx.app.ui.theme.WinxPurple
import com.winx.app.ui.theme.WinxWhite


@Composable
fun DashboardScreen(
    onAddEntryClick: () -> Unit = {},
    onAddCountryClick: () -> Unit = {},
    onEntryClick: () -> Unit = {},
    onCalendarClick: () -> Unit = {},
    onLibraryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {}
) {

    val context = LocalContext.current
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val profilePreferences = context.getSharedPreferences("winx_profile", Context.MODE_PRIVATE)
    val displayName = profilePreferences.getString("name", null)
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: firebaseUser?.displayName?.trim()?.takeIf { it.isNotBlank() }
        ?: "User"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WinxWhite)
    ) {

        // =========================================================
        // SCROLLABLE DASHBOARD CONTENT
        // =========================================================

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))


            // ---------------------------------------------------------
            // TOP BAR
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = T("Winx"),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(WinxLightDashboard)
                            .clickable { onNotificationsClick() },
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.NotificationsNone,
                            contentDescription = "Notifications",
                            tint = WinxDarkBlue,
                            modifier = Modifier.size(23.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(WinxPink),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Profile",
                            tint = WinxDarkBlue,
                            modifier = Modifier.size(23.dp)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(20.dp))


            // ---------------------------------------------------------
            // WELCOME CARD
            // ---------------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxOrange
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = winxGreeting(displayName),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = WinxDarkBlue
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = T("Welcome Back"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = WinxDarkBlue
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = T("Every Place has a story,\nwhat will you capture today??"),
                        fontSize = 15.sp,
                        color = WinxDarkBlue,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))


                    // Add Entry + Add Country buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Button(
                            onClick = onAddEntryClick,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WinxDarkBlue
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = null,
                                tint = WinxWhite,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = T("Add Entry"),
                                color = WinxWhite
                            )
                        }


                        Button(
                            onClick = onAddCountryClick,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WinxPurple
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Public,
                                contentDescription = null,
                                tint = WinxDarkBlue,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = T("Add Country"),
                                color = WinxDarkBlue
                            )
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            // ---------------------------------------------------------
            // CALENDAR SECTION
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = T("Calendar"),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                TextButton(
                    onClick = onCalendarClick
                ) {

                    Text(
                        text = T("View All"),
                        color = WinxBlue
                    )

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = WinxBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxLightPink
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = T("September 2026"),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = WinxDarkBlue
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        CalendarDay("M", "14")
                        CalendarDay("T", "15")
                        CalendarDay("W", "16", selected = true)
                        CalendarDay("T", "17")
                        CalendarDay("F", "18")
                        CalendarDay("S", "19")
                        CalendarDay("S", "20")
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            // ---------------------------------------------------------
            // ENTRIES SECTION
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = T("Entries"),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                TextButton(
                    onClick = onEntryClick
                ) {

                    Text(
                        text = T("View All"),
                        color = WinxBlue
                    )

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = WinxBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))


            EntryCard(
                title = "Cape Town",
                description = "A beautiful day exploring the city.",
                date = "16 September 2026",
                backgroundColor = WinxBlue
            )


            Spacer(modifier = Modifier.height(12.dp))


            EntryCard(
                title = "Durban",
                description = "Amazing food and unforgettable moments.",
                date = "10 September 2026",
                backgroundColor = WinxPurple
            )


            Spacer(modifier = Modifier.height(24.dp))


            // ---------------------------------------------------------
            // RECENT DISHES
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = T("Recent Dishes"),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                TextButton(
                    onClick = onEntryClick
                ) {

                    Text(
                        text = T("View All"),
                        color = WinxBlue
                    )

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = WinxBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                DishCard(
                    name = "Pizza",
                    location = "Cape Town",
                    modifier = Modifier.weight(1f)
                )

                DishCard(
                    name = "Bunny Chow",
                    location = "Durban",
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))
        }


        // =============================================================
        // BOTTOM NAVIGATION
        // =============================================================

        BottomNavigationBar(
            onEntryClick = onEntryClick,
            onCalendarClick = onCalendarClick,
            onLibraryClick = onLibraryClick,
            onSettingsClick = onSettingsClick
        )
    }
}


// =====================================================================
// CALENDAR DAY
// =====================================================================

@Composable
private fun CalendarDay(
    day: String,
    date: String,
    selected: Boolean = false
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = day,
            fontSize = 12.sp,
            color = WinxGreyDashboard
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(
                    if (selected) {
                        WinxBlue
                    } else {
                        Color.Transparent
                    }
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = date,
                fontSize = 13.sp,
                fontWeight =
                    if (selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                color =
                    if (selected) {
                        WinxWhite
                    } else {
                        WinxDarkBlue
                    }
            )
        }
    }
}


// =====================================================================
// ENTRY CARD
// =====================================================================

@Composable
private fun EntryCard(
    title: String,
    description: String,
    date: String,
    backgroundColor: Color
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                backgroundColor.copy(alpha = 0.12f)
        )
    ) {

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(65.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.Description,
                    contentDescription = "Entry",
                    tint = WinxWhite,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = WinxGreyDashboard
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = date,
                    fontSize = 11.sp,
                    color = WinxBlue
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "Open entry",
                tint = WinxDarkBlue,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


// =====================================================================
// DISH CARD
// =====================================================================

@Composable
private fun DishCard(
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = WinxLightPink
        )
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(WinxOrange),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = "Dish",
                    tint = WinxDarkBlue,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Text(
                text = location,
                fontSize = 12.sp,
                color = WinxGreyDashboard
            )
        }
    }
}


// =====================================================================
// BOTTOM NAVIGATION BAR
// =====================================================================

@Composable
fun BottomNavigationBar(
    selectedItem: String = "Dashboard",
    onDashboardClick: () -> Unit = {},
    onEntryClick: () -> Unit = {},
    onCalendarClick: () -> Unit = {},
    onLibraryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .background(WinxWhite)
    ) {

        HorizontalDivider(
            color = WinxLightDashboard
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavigationItem(
                icon = Icons.Outlined.Home,
                label = "Dashboard",
                selected = selectedItem == "Dashboard",
                onClick = onDashboardClick
            )

            NavigationItem(
                icon = Icons.Outlined.Description,
                label = "Entry",
                selected = selectedItem == "Entry",
                onClick = onEntryClick
            )

            NavigationItem(
                icon = Icons.Outlined.CalendarMonth,
                label = "Calendar",
                selected = selectedItem == "Calendar",
                onClick = onCalendarClick
            )

            NavigationItem(
                icon = Icons.Outlined.Folder,
                label = "Library",
                selected = selectedItem == "Library",
                onClick = onLibraryClick
            )

            NavigationItem(
                icon = Icons.Outlined.Settings,
                label = "Settings",
                selected = selectedItem == "Settings",
                onClick = onSettingsClick
            )
        }
    }
}


// =====================================================================
// NAVIGATION ITEM
// =====================================================================

@Composable
private fun NavigationItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {

    TextButton(
        onClick = onClick
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = label,
                tint =
                    if (selected) {
                        WinxBlue
                    } else {
                        WinxGreyDashboard
                    },
                modifier = Modifier.size(23.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = label,
                fontSize = 10.sp,
                color =
                    if (selected) {
                        WinxBlue
                    } else {
                        WinxGreyDashboard
                    }
            )
        }
    }
}


// =====================================================================
// DASHBOARD SUPPORTING COLOURS
// =====================================================================

private val WinxLightDashboard =
    Color(0xFFF5F5F5)

private val WinxGreyDashboard =
    Color(0xFF777777)