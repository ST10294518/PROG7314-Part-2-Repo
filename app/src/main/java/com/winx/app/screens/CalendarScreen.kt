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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun CalendarScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedDay by remember {
        mutableIntStateOf(16)
    }

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
                .padding(
                    start = 12.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = WinxDarkBlue
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column {

                Text(
                    text = "Calendar",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Text(
                    text = "Your travel memories at a glance",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CalendarGrey
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            // ---------------------------------------------------------
            // MONTH CARD
            // ---------------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WinxLightPink
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    // Month controls
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = {
                                // Previous month functionality
                                // will be connected in the next increment.
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ChevronLeft,
                                contentDescription = "Previous month",
                                tint = WinxDarkBlue
                            )
                        }

                        Text(
                            text = "September 2026",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = WinxDarkBlue
                        )

                        IconButton(
                            onClick = {
                                // Next month functionality
                                // will be connected in the next increment.
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ChevronRight,
                                contentDescription = "Next month",
                                tint = WinxDarkBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Days of week
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CalendarWeekHeading("M", Modifier.weight(1f))
                        CalendarWeekHeading("T", Modifier.weight(1f))
                        CalendarWeekHeading("W", Modifier.weight(1f))
                        CalendarWeekHeading("T", Modifier.weight(1f))
                        CalendarWeekHeading("F", Modifier.weight(1f))
                        CalendarWeekHeading("S", Modifier.weight(1f))
                        CalendarWeekHeading("S", Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // September 2026 starts on a Tuesday.
                    CalendarWeek(
                        days = listOf(null, 1, 2, 3, 4, 5, 6),
                        selectedDay = selectedDay,
                        onDaySelected = {
                            selectedDay = it
                        }
                    )

                    CalendarWeek(
                        days = listOf(7, 8, 9, 10, 11, 12, 13),
                        selectedDay = selectedDay,
                        onDaySelected = {
                            selectedDay = it
                        }
                    )

                    CalendarWeek(
                        days = listOf(14, 15, 16, 17, 18, 19, 20),
                        selectedDay = selectedDay,
                        onDaySelected = {
                            selectedDay = it
                        }
                    )

                    CalendarWeek(
                        days = listOf(21, 22, 23, 24, 25, 26, 27),
                        selectedDay = selectedDay,
                        onDaySelected = {
                            selectedDay = it
                        }
                    )

                    CalendarWeek(
                        days = listOf(28, 29, 30, null, null, null, null),
                        selectedDay = selectedDay,
                        onDaySelected = {
                            selectedDay = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------------------------------------------------------
            // SELECTED DATE
            // ---------------------------------------------------------

            Text(
                text = "$selectedDay September 2026",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Memories for this day",
                fontSize = 14.sp,
                color = CalendarGrey
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Example content for the selected prototype date.
            if (selectedDay == 16) {

                CalendarMemoryCard(
                    title = "Cape Town",
                    description = "A beautiful day exploring the city.",
                    iconBackground = WinxBlue
                )

                Spacer(modifier = Modifier.height(12.dp))

                CalendarMemoryCard(
                    title = "Travel Memory",
                    description = "Captured on 16 September 2026.",
                    iconBackground = WinxPurple
                )

            } else {

                EmptyCalendarDay()
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}


// =====================================================================
// WEEK HEADING
// =====================================================================

@Composable
private fun CalendarWeekHeading(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = CalendarGrey
    )
}


// =====================================================================
// CALENDAR WEEK
// =====================================================================

@Composable
private fun CalendarWeek(
    days: List<Int?>,
    selectedDay: Int,
    onDaySelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        days.forEach { day ->

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {

                if (day != null) {

                    CalendarDate(
                        day = day,
                        selected = day == selectedDay,
                        hasMemory = day == 10 || day == 16,
                        onClick = {
                            onDaySelected(day)
                        }
                    )
                }
            }
        }
    }
}


// =====================================================================
// CALENDAR DATE
// =====================================================================

@Composable
private fun CalendarDate(
    day: Int,
    selected: Boolean,
    hasMemory: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(
                    if (selected) {
                        WinxBlue
                    } else {
                        Color.Transparent
                    }
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = day.toString(),
                fontSize = 13.sp,
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
                color = if (selected) {
                    WinxWhite
                } else {
                    WinxDarkBlue
                }
            )
        }

        if (hasMemory) {

            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(WinxOrange)
            )
        }
    }
}


// =====================================================================
// MEMORY CARD
// =====================================================================

@Composable
private fun CalendarMemoryCard(
    title: String,
    description: String,
    iconBackground: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = iconBackground.copy(alpha = 0.10f)
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.Place,
                    contentDescription = null,
                    tint = WinxWhite,
                    modifier = Modifier.size(27.dp)
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

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = CalendarGrey
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "Open memory",
                tint = WinxDarkBlue
            )
        }
    }
}


// =====================================================================
// EMPTY DATE
// =====================================================================

@Composable
private fun EmptyCalendarDay() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = WinxLightPink
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Outlined.Event,
                contentDescription = null,
                tint = WinxBlue,
                modifier = Modifier.size(38.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "No memories for this day",
                fontWeight = FontWeight.SemiBold,
                color = WinxDarkBlue
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Your travel memories will appear here.",
                fontSize = 13.sp,
                color = CalendarGrey,
                textAlign = TextAlign.Center
            )
        }
    }
}


// =====================================================================
// CALENDAR SUPPORTING COLOUR
// =====================================================================

private val CalendarGrey = Color(0xFF777777)