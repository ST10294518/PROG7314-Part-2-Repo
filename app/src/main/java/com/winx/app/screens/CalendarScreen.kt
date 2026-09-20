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
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.winx.app.ui.theme.WinxWhite


private data class CalendarEntryGroup(
    val date: String,
    val entryCount: Int,
    val visibleEntries: Int,
    val extraEntries: Int = 0
)


@Composable
fun CalendarScreen(
    onDashboardClick: () -> Unit = {},
    onEntryClick: () -> Unit = {},
    onLibraryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {

    var selectedDay by remember {
        mutableIntStateOf(17)
    }

    val entryGroups = remember {
        listOf(
            CalendarEntryGroup(
                date = "Thursday, April 17, 2025",
                entryCount = 5,
                visibleEntries = 3,
                extraEntries = 2
            ),
            CalendarEntryGroup(
                date = "Friday, April 18, 2025",
                entryCount = 3,
                visibleEntries = 3
            ),
            CalendarEntryGroup(
                date = "Thursday, March 17, 2025",
                entryCount = 2,
                visibleEntries = 2
            ),
            CalendarEntryGroup(
                date = "Thursday, Feb 1, 2025",
                entryCount = 4,
                visibleEntries = 3,
                extraEntries = 1
            )
        )
    }

    Scaffold(
        containerColor = WinxLightPink,
        bottomBar = {
            BottomNavigationBar(
                selectedItem = "Calendar",
                onDashboardClick = onDashboardClick,
                onEntryClick = onEntryClick,
                onCalendarClick = {},
                onLibraryClick = onLibraryClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // ---------------------------------------------------------
            // HEADER
            // ---------------------------------------------------------

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 10.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Calendar",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Text(
                    text = "Your moments by date",
                    fontSize = 12.sp,
                    color = CalendarGrey
                )
            }

            // ---------------------------------------------------------
            // COMPACT CALENDAR
            // ---------------------------------------------------------

            CompactCalendar(
                selectedDay = selectedDay,
                onDaySelected = {
                    selectedDay = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ---------------------------------------------------------
            // ENTRY LIST
            // ---------------------------------------------------------

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = 16.dp
                    )
            ) {

                entryGroups.forEach { group ->

                    CalendarEntryCard(
                        group = group
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(80.dp)
                )
            }
        }
    }
}


@Composable
private fun CompactCalendar(
    selectedDay: Int,
    onDaySelected: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = WinxWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 5.dp
                )
        ) {

            // Month selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = {
                        // Previous month will be connected
                        // when backend/calendar logic is added.
                    },
                    modifier = Modifier.size(32.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.Outlined.ChevronLeft,
                        contentDescription =
                            "Previous month",
                        tint = CalendarGrey
                    )
                }

                Text(
                    text = "April 2025",
                    color = WinxDarkBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = {
                        // Next month will be connected
                        // when calendar logic is added.
                    },
                    modifier = Modifier.size(32.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.Outlined.ChevronRight,
                        contentDescription =
                            "Next month",
                        tint = CalendarGrey
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            // Week headings
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                listOf(
                    "Mon",
                    "Tue",
                    "Wed",
                    "Thu",
                    "Fri",
                    "Sat",
                    "Sun"
                ).forEach { heading ->

                    Text(
                        text = heading,
                        modifier =
                            Modifier.weight(1f),
                        textAlign =
                            TextAlign.Center,
                        fontSize = 9.sp,
                        fontWeight =
                            FontWeight.SemiBold,
                        color = CalendarGrey
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // Prototype week
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                listOf(
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20
                ).forEach { day ->

                    CompactCalendarDay(
                        day = day,
                        selected =
                            day == selectedDay,
                        hasEntry =
                            day == 15 ||
                                    day == 17 ||
                                    day == 18,
                        onClick = {
                            onDaySelected(day)
                        },
                        modifier =
                            Modifier.weight(1f)
                    )
                }
            }
        }
    }
}


@Composable
private fun CompactCalendarDay(
    day: Int,
    selected: Boolean,
    hasEntry: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clickable {
                onClick()
            },
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(
                    if (selected) {
                        WinxBlue
                    } else {
                        Color.Transparent
                    }
                ),
            contentAlignment =
                Alignment.Center
        ) {

            Text(
                text = day.toString(),
                fontSize = 11.sp,
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

        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(3.dp)
                .clip(CircleShape)
                .background(
                    if (hasEntry) {
                        WinxBlue
                    } else {
                        Color.Transparent
                    }
                )
        )
    }
}


@Composable
private fun CalendarEntryCard(
    group: CalendarEntryGroup
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = WinxOrange
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            // ---------------------------------------------------------
            // DATE + ENTRY COUNT
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = group.date,
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Text(
                    text =
                        "${group.entryCount} " +
                                if (group.entryCount == 1) {
                                    "Entry"
                                } else {
                                    "Entries"
                                },
                    fontSize = 11.sp,
                    fontWeight =
                        FontWeight.SemiBold,
                    color = WinxDarkBlue
                )

                Spacer(
                    modifier = Modifier.width(2.dp)
                )

                Icon(
                    imageVector =
                        Icons.Outlined.ExpandLess,
                    contentDescription =
                        "Collapse entries",
                    modifier = Modifier.size(17.dp),
                    tint = WinxDarkBlue
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // ---------------------------------------------------------
            // MEMORY PREVIEWS
            // ---------------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp),
                verticalAlignment =
                    Alignment.Top
            ) {

                repeat(group.visibleEntries) { index ->

                    CalendarMemoryPreview(
                        isVideo = index == 1,
                        showDetails = index == 0,
                        modifier =
                            Modifier.weight(1f)
                    )
                }

                if (group.extraEntries > 0) {

                    MoreEntriesCard(
                        count = group.extraEntries,
                        modifier =
                            Modifier.weight(0.75f)
                    )
                }
            }
        }
    }
}


@Composable
private fun CalendarMemoryPreview(
    isVideo: Boolean,
    showDetails: Boolean,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .clip(
                    RoundedCornerShape(7.dp)
                )
                .background(
                    if (isVideo) {
                        WinxDarkBlue
                    } else {
                        WinxWhite
                    }
                ),
            contentAlignment =
                Alignment.Center
        ) {

            Icon(
                imageVector =
                    if (isVideo) {
                        Icons.Filled.PlayArrow
                    } else {
                        Icons.Filled.Image
                    },
                contentDescription =
                    if (isVideo) {
                        "Video entry"
                    } else {
                        "Photo entry"
                    },
                tint =
                    if (isVideo) {
                        WinxWhite
                    } else {
                        WinxBlue
                    },
                modifier = Modifier.size(25.dp)
            )

            if (isVideo) {

                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(
                            WinxWhite.copy(
                                alpha = 0.85f
                            )
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(
                        imageVector =
                            Icons.Filled.PlayArrow,
                        contentDescription =
                            "Play video",
                        tint = WinxBlue,
                        modifier =
                            Modifier.size(17.dp)
                    )
                }
            }
        }

        if (showDetails) {

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "La Trattoria",
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue,
                maxLines = 1
            )

            Text(
                text = "Rome, Italy • 1:30pm",
                fontSize = 7.sp,
                color = WinxDarkBlue,
                maxLines = 1
            )
        }
    }
}


@Composable
private fun MoreEntriesCard(
    count: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(62.dp)
            .clip(
                RoundedCornerShape(7.dp)
            )
            .background(
                WinxWhite.copy(
                    alpha = 0.90f
                )
            ),
        contentAlignment =
            Alignment.Center
    ) {

        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                text = "+$count",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Text(
                text = "More",
                fontSize = 9.sp,
                color = CalendarGrey
            )
        }
    }
}


private val CalendarGrey =
    Color(0xFF777777)