package com.winx.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.winx.app.ui.theme.WinxBlue
import com.winx.app.ui.theme.WinxDarkBlue
import com.winx.app.ui.theme.WinxLightPink
import com.winx.app.ui.theme.WinxOrange
import com.winx.app.ui.theme.WinxPurple
import com.winx.app.ui.theme.WinxWhite

@Composable
fun EntryScreen(
    onPicturesClick: () -> Unit = {},
    onVideosClick: () -> Unit = {},
    onCountriesClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {

    var title by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WinxWhite)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // ---------------------------------------------------------
            // HEADER
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Entry",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ---------------------------------------------------------
            // MEDIA OPTIONS
            // ---------------------------------------------------------

            Text(
                text = "Add to your journey",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                MediaOption(
                    title = "Pictures",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Image,
                            contentDescription = "Add pictures",
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    backgroundColor = WinxBlue,
                    onClick = onPicturesClick,
                    modifier = Modifier.weight(1f)
                )

                MediaOption(
                    title = "Videos",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Videocam,
                            contentDescription = "Add videos",
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    backgroundColor = WinxPurple,
                    onClick = onVideosClick,
                    modifier = Modifier.weight(1f)
                )

                MediaOption(
                    title = "Countries",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "Add country",
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    backgroundColor = WinxOrange,
                    onClick = onCountriesClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ---------------------------------------------------------
            // UPLOAD AREA
            // ---------------------------------------------------------

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxDarkBlue
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(WinxBlue),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Upload",
                            tint = WinxWhite,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Add photos or videos",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = WinxWhite
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Capture the moments that made this journey special",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------------------------------------------------------
            // BASIC DETAILS
            // ---------------------------------------------------------

            Text(
                text = "Basic Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Entry Title")
                },
                placeholder = {
                    Text("e.g. Amazing day in Cape Town")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Location")
                },
                placeholder = {
                    Text("Where did you go?")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Date field
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxLightPink
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = "Date",
                        tint = WinxBlue,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Date",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = "19 September 2026",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = WinxDarkBlue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ---------------------------------------------------------
            // RATING
            // ---------------------------------------------------------

            Text(
                text = "Rate your experience",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = WinxDarkBlue
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                for (star in 1..5) {

                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Rating $star",
                        tint = if (star <= rating) {
                            WinxOrange
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                rating = star
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ---------------------------------------------------------
            // NOTES
            // ---------------------------------------------------------

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                label = {
                    Text("Notes")
                },
                placeholder = {
                    Text("Tell us about your experience...")
                },
                maxLines = 5,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ---------------------------------------------------------
            // ACTION BUTTONS
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TextButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = WinxDarkBlue,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Cancel",
                        color = WinxDarkBlue
                    )
                }

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WinxPurple
                    )
                ) {

                    Text(
                        text = "Save Entry",
                        color = WinxDarkBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // -------------------------------------------------------------
        // BOTTOM NAVIGATION
        // -------------------------------------------------------------

        EntryBottomNavigation(
            onEntryClick = {},
            onCountriesClick = onCountriesClick
        )
    }
}


// =====================================================================
// MEDIA OPTION
// =====================================================================

@Composable
private fun MediaOption(
    title: String,
    icon: @Composable () -> Unit,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(92.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (backgroundColor == WinxOrange) {
                    WinxDarkBlue
                } else {
                    WinxWhite
                }
            )
        }
    }
}


// =====================================================================
// ENTRY BOTTOM NAVIGATION
// =====================================================================

@Composable
private fun EntryBottomNavigation(
    onEntryClick: () -> Unit,
    onCountriesClick: () -> Unit
) {

    androidx.compose.material3.HorizontalDivider(
        color = Color(0xFFF0F0F0)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(68.dp)
            .background(WinxWhite),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        EntryNavigationItem(
            icon = Icons.Outlined.Image,
            label = "Entry",
            selected = true,
            onClick = onEntryClick
        )

        EntryNavigationItem(
            icon = Icons.Outlined.LocationOn,
            label = "Countries",
            onClick = onCountriesClick
        )
    }
}


// =====================================================================
// ENTRY NAVIGATION ITEM
// =====================================================================

@Composable
private fun EntryNavigationItem(
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
                tint = if (selected) {
                    WinxBlue
                } else {
                    Color.Gray
                },
                modifier = Modifier.size(23.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = label,
                fontSize = 10.sp,
                color = if (selected) {
                    WinxBlue
                } else {
                    Color.Gray
                }
            )
        }
    }
}