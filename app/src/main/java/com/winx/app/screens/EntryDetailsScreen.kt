package com.winx.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.winx.app.ui.theme.WinxBlue
import com.winx.app.ui.theme.WinxDarkBlue
import com.winx.app.ui.theme.WinxLightPink
import com.winx.app.ui.theme.WinxOrange
import com.winx.app.ui.theme.WinxWhite


@Composable
fun EntryDetailsScreen(
    entry: TravelEntry,
    onBackClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WinxWhite)
    ) {

        // ---------------------------------------------------------
        // HEADER
        // ---------------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WinxDarkBlue)
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBackClick
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = WinxWhite
                )
            }

            Text(
                text = "Entry Details",
                color = WinxWhite,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // ---------------------------------------------------------
        // ENTRY CONTENT
        // ---------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            // -----------------------------------------------------
            // TITLE
            // -----------------------------------------------------

            Text(
                text = entry.title.ifBlank {
                    "Untitled Entry"
                },
                color = WinxDarkBlue,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            // -----------------------------------------------------
            // LOCATION
            // -----------------------------------------------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = WinxBlue,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = when {
                        entry.location.isNotBlank() &&
                                entry.country.isNotBlank() -> {
                            "${entry.location}, ${entry.country}"
                        }

                        entry.location.isNotBlank() -> {
                            entry.location
                        }

                        entry.country.isNotBlank() -> {
                            entry.country
                        }

                        else -> {
                            "Location not specified"
                        }
                    },
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // -----------------------------------------------------
            // COUNTRY
            // -----------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxLightPink
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Country",
                        tint = WinxOrange,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Column {

                        Text(
                            text = "Country",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier.height(3.dp)
                        )

                        Text(
                            text = entry.country.ifBlank {
                                "Not specified"
                            },
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = WinxDarkBlue
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(14.dp)
            )


            // -----------------------------------------------------
            // DATE
            // -----------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxLightPink
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = "Date",
                        tint = WinxBlue,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Column {

                        Text(
                            text = "Date",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier.height(3.dp)
                        )

                        Text(
                            text = "19 September 2026",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = WinxDarkBlue
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(18.dp)
            )


            // -----------------------------------------------------
            // RATING
            // -----------------------------------------------------

            Text(
                text = "Your Rating",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                for (star in 1..5) {

                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Rating $star",
                        tint = if (star <= entry.rating) {
                            WinxOrange
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier.size(30.dp)
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // -----------------------------------------------------
            // NOTES
            // -----------------------------------------------------

            Text(
                text = "Notes",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F8F8)
                )
            ) {

                Text(
                    text = entry.notes.ifBlank {
                        "No notes were added for this entry."
                    },
                    modifier = Modifier.padding(16.dp),
                    fontSize = 15.sp,
                    color = WinxDarkBlue
                )
            }
        }
    }
}