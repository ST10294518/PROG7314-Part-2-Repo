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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import com.winx.app.ui.theme.WinxWhite


private data class LibraryMemory(
    val title: String,
    val location: String,
    val time: String
)

private data class LibraryGroup(
    val date: String,
    val memories: List<LibraryMemory>
)

private data class CountryMemory(
    val flag: String,
    val country: String,
    val location: String,
    val venues: Int,
    val photos: Int,
    val videos: Int,
    val dateRange: String
)


@Composable
fun LibraryScreen() {

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WinxLightPink)
    ) {

        // ---------------------------------------------------------
        // HEADER
        // ---------------------------------------------------------

        LibraryHeader()

        // ---------------------------------------------------------
        // WHITE SEARCH / TAB PANEL
        // ---------------------------------------------------------

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = WinxWhite
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                LibraryTabs(
                    selectedTab = selectedTab,
                    onTabSelected = {
                        selectedTab = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        placeholder = {
                            Text(
                                text =
                                    if (selectedTab == 0) {
                                        "Search photos by place, date or notes..."
                                    } else if (selectedTab == 1) {
                                        "Search videos by place, date or notes..."
                                    } else {
                                        "Search entries by country or place..."
                                    },
                                fontSize = 11.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector =
                                    Icons.Outlined.Search,
                                contentDescription =
                                    "Search",
                                modifier =
                                    Modifier.size(18.dp)
                            )
                        },
                        singleLine = true,
                        shape =
                            RoundedCornerShape(8.dp),
                        colors =
                            OutlinedTextFieldDefaults.colors(
                                focusedContainerColor =
                                    LibrarySearchGrey,
                                unfocusedContainerColor =
                                    LibrarySearchGrey
                            )
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Card(
                        modifier = Modifier
                            .height(42.dp)
                            .clickable {
                                // Filter functionality
                                // will be connected later.
                            },
                        shape =
                            RoundedCornerShape(8.dp),
                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    LibrarySearchGrey
                            )
                    ) {

                        Row(
                            modifier = Modifier
                                .height(42.dp)
                                .padding(
                                    horizontal = 12.dp
                                ),
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Filled.FilterList,
                                contentDescription =
                                    "Filter",
                                modifier =
                                    Modifier.size(17.dp),
                                tint = LibraryGrey
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(4.dp)
                            )

                            Text(
                                text = "Filter",
                                fontSize = 11.sp,
                                color = LibraryGrey
                            )
                        }
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // ---------------------------------------------------------
        // TAB CONTENT
        // ---------------------------------------------------------

        when (selectedTab) {

            0 -> {
                PhotoLibraryContent(
                    searchText = searchText
                )
            }

            1 -> {
                VideoLibraryContent(
                    searchText = searchText
                )
            }

            2 -> {
                EntryLibraryContent(
                    searchText = searchText
                )
            }
        }
    }
}


@Composable
private fun LibraryHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 12.dp,
                top = 14.dp,
                bottom = 10.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier.width(40.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Library",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = WinxDarkBlue
                )

                Text(
                    text = "Your saved memories",
                    fontSize = 10.sp,
                    color = LibraryGrey
                )
            }

            IconButton(
                onClick = {
                    // Notifications will be
                    // connected later.
                }
            ) {

                Icon(
                    imageVector =
                        Icons.Outlined.Notifications,
                    contentDescription =
                        "Notifications",
                    tint = WinxDarkBlue
                )
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    WinxDarkBlue.copy(
                        alpha = 0.25f
                    )
                )
        )
    }
}


@Composable
private fun LibraryTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {

    val labels = listOf(
        "Photos",
        "Videos",
        "Entries"
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        labels.forEachIndexed { index, label ->

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onTabSelected(index)
                    },
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector =
                            when (index) {
                                0 -> Icons.Filled.Image
                                1 -> Icons.Filled.PlayArrow
                                else -> Icons.Outlined.Place
                            },
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint =
                            if (
                                selectedTab == index
                            ) {
                                WinxBlue
                            } else {
                                WinxDarkBlue
                            }
                    )

                    Spacer(
                        modifier = Modifier.width(2.dp)
                    )

                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight =
                            if (
                                selectedTab == index
                            ) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                        color =
                            if (
                                selectedTab == index
                            ) {
                                WinxBlue
                            } else {
                                WinxDarkBlue
                            }
                    )
                }

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(0.55f)
                        .background(
                            if (
                                selectedTab == index
                            ) {
                                WinxBlue
                            } else {
                                Color.Transparent
                            }
                        )
                )
            }
        }
    }
}


// ================================================================
// PHOTO TAB
// ================================================================

@Composable
private fun PhotoLibraryContent(
    searchText: String
) {

    val groups = remember {
        listOf(
            LibraryGroup(
                date = "June 1, 2025",
                memories = listOf(
                    LibraryMemory(
                        "Locanda Don Serafino",
                        "Ragusa, Italy",
                        "6h"
                    ),
                    LibraryMemory(
                        "Fangweng Restaurant",
                        "Yichang, China",
                        "6h"
                    ),
                    LibraryMemory(
                        "Enoteca Maria",
                        "New York, USA",
                        "1:30pm"
                    )
                )
            ),

            LibraryGroup(
                date = "May 25, 2025",
                memories = listOf(
                    LibraryMemory(
                        "Noksu",
                        "New York, USA",
                        "6:00pm"
                    ),
                    LibraryMemory(
                        "Under",
                        "Lindesnes, Norway",
                        "6:30pm"
                    ),
                    LibraryMemory(
                        "Iris",
                        "Rosendal, Norway",
                        "7:00pm"
                    )
                )
            ),

            LibraryGroup(
                date = "April 17, 2025",
                memories = listOf(
                    LibraryMemory(
                        "La Trattoria",
                        "Rome, Italy",
                        "1:30pm"
                    ),
                    LibraryMemory(
                        "Cafe de Flore",
                        "Paris, France",
                        "6:30pm"
                    ),
                    LibraryMemory(
                        "Sushi Zen",
                        "Tokyo, Japan",
                        "7:30pm"
                    )
                )
            )
        )
    }

    val filteredGroups =
        if (searchText.isBlank()) {
            groups
        } else {
            groups.mapNotNull { group ->

                val memories =
                    group.memories.filter {
                        it.title.contains(
                            searchText,
                            ignoreCase = true
                        ) ||
                                it.location.contains(
                                    searchText,
                                    ignoreCase = true
                                ) ||
                                group.date.contains(
                                    searchText,
                                    ignoreCase = true
                                )
                    }

                if (memories.isEmpty()) {
                    null
                } else {
                    group.copy(
                        memories = memories
                    )
                }
            }
        }

    MemoryGroupList(
        groups = filteredGroups,
        typeName = "Photos",
        isVideo = false
    )
}


// ================================================================
// VIDEO TAB
// ================================================================

@Composable
private fun VideoLibraryContent(
    searchText: String
) {

    val groups = remember {
        listOf(
            LibraryGroup(
                date = "April 17, 2025",
                memories = listOf(
                    LibraryMemory(
                        "Rome Evening",
                        "Rome, Italy",
                        "8:10pm"
                    ),
                    LibraryMemory(
                        "Paris Walk",
                        "Paris, France",
                        "5:45pm"
                    ),
                    LibraryMemory(
                        "Tokyo Nights",
                        "Tokyo, Japan",
                        "9:00pm"
                    )
                )
            ),

            LibraryGroup(
                date = "April 15, 2025",
                memories = listOf(
                    LibraryMemory(
                        "Le Jules Verne",
                        "Paris, France",
                        "7:00pm"
                    ),
                    LibraryMemory(
                        "Giraffe Manor",
                        "Nairobi, Kenya",
                        "10:30am"
                    ),
                    LibraryMemory(
                        "The Grotto",
                        "Krabi, Thailand",
                        "7:30pm"
                    )
                )
            )
        )
    }

    val filtered =
        if (searchText.isBlank()) {
            groups
        } else {
            groups.mapNotNull { group ->

                val memories =
                    group.memories.filter {
                        it.title.contains(
                            searchText,
                            true
                        ) ||
                                it.location.contains(
                                    searchText,
                                    true
                                )
                    }

                if (memories.isEmpty()) {
                    null
                } else {
                    group.copy(
                        memories = memories
                    )
                }
            }
        }

    MemoryGroupList(
        groups = filtered,
        typeName = "Videos",
        isVideo = true
    )
}


// ================================================================
// PHOTO / VIDEO GROUP LIST
// ================================================================

@Composable
private fun MemoryGroupList(
    groups: List<LibraryGroup>,
    typeName: String,
    isVideo: Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(horizontal = 16.dp)
    ) {

        groups.forEach { group ->

            Card(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(8.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            WinxOrange
                    ),
                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Text(
                            text = group.date,
                            modifier =
                                Modifier.weight(1f),
                            fontSize = 12.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color = WinxDarkBlue
                        )

                        Text(
                            text =
                                "${group.memories.size} $typeName ⌃",
                            fontSize = 11.sp,
                            fontWeight =
                                FontWeight.SemiBold,
                            color = WinxDarkBlue
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    group.memories.forEachIndexed {
                            index,
                            memory ->

                        LibraryMemoryRow(
                            memory = memory,
                            isVideo = isVideo
                        )

                        if (
                            index <
                            group.memories.lastIndex
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 62.dp
                                    )
                                    .height(1.dp)
                                    .background(
                                        WinxDarkBlue.copy(
                                            alpha = 0.15f
                                        )
                                    )
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}


@Composable
private fun LibraryMemoryRow(
    memory: LibraryMemory,
    isVideo: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(
                    width = 56.dp,
                    height = 48.dp
                )
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
                contentDescription = null,
                tint =
                    if (isVideo) {
                        WinxWhite
                    } else {
                        WinxBlue
                    }
            )
        }

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = memory.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = WinxDarkBlue
            )

            Text(
                text = "● ${memory.location}",
                fontSize = 8.sp,
                color = WinxDarkBlue
            )

            Text(
                text = "◷ ${memory.time}",
                fontSize = 8.sp,
                color = WinxDarkBlue
            )
        }

        Row {

            repeat(5) {

                Icon(
                    imageVector =
                        Icons.Outlined.Star,
                    contentDescription = null,
                    modifier =
                        Modifier.size(11.dp),
                    tint = WinxDarkBlue
                )
            }
        }

        Icon(
            imageVector =
                Icons.Filled.MoreVert,
            contentDescription =
                "More options",
            modifier = Modifier.size(17.dp),
            tint = WinxDarkBlue
        )
    }
}


// ================================================================
// ENTRIES TAB
// ================================================================

@Composable
private fun EntryLibraryContent(
    searchText: String
) {

    val countries = remember {
        listOf(
            CountryMemory(
                flag = "🇮🇹",
                country = "Italy",
                location =
                    "Rome, Naples, Florence & more",
                venues = 8,
                photos = 14,
                videos = 4,
                dateRange =
                    "Apr 18 - Apr 21, 2025"
            ),

            CountryMemory(
                flag = "🇯🇲",
                country = "Jamaica",
                location =
                    "Negril, Montego Bay & more",
                venues = 6,
                photos = 12,
                videos = 3,
                dateRange =
                    "Apr 10 - Apr 17, 2025"
            ),

            CountryMemory(
                flag = "🇯🇵",
                country = "Japan",
                location =
                    "Tokyo, Nagoya, Kyoto & more",
                venues = 10,
                photos = 18,
                videos = 5,
                dateRange =
                    "Apr 2 - Apr 10, 2025"
            ),

            CountryMemory(
                flag = "🇪🇸",
                country = "Spain",
                location =
                    "Rome, Naples, Florence & more",
                venues = 7,
                photos = 16,
                videos = 4,
                dateRange =
                    "Mar 18 - Apr 1, 2025"
            )
        )
    }

    val filteredCountries =
        if (searchText.isBlank()) {
            countries
        } else {
            countries.filter {
                it.country.contains(
                    searchText,
                    ignoreCase = true
                ) ||
                        it.location.contains(
                            searchText,
                            ignoreCase = true
                        )
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(horizontal = 16.dp)
    ) {

        // Summary card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape =
                RoundedCornerShape(6.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor =
                        WinxWhite
                ),
            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "🌎",
                    fontSize = 22.sp
                )

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text =
                            "You've visited 8 countries",
                        fontSize = 12.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color = WinxDarkBlue
                    )

                    Text(
                        text =
                            "Keep exploring and adding more memories",
                        fontSize = 9.sp,
                        color = LibraryGrey
                    )
                }

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "32",
                        fontSize = 15.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color = WinxDarkBlue
                    )

                    Text(
                        text = "Venues",
                        fontSize = 9.sp,
                        color = LibraryGrey
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape =
                RoundedCornerShape(8.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor =
                        WinxOrange
                ),
            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                filteredCountries.forEachIndexed {
                        index,
                        country ->

                    CountryEntryRow(
                        country = country
                    )

                    if (
                        index <
                        filteredCountries.lastIndex
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 68.dp
                                )
                                .height(1.dp)
                                .background(
                                    WinxDarkBlue.copy(
                                        alpha = 0.15f
                                    )
                                )
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}


@Composable
private fun CountryEntryRow(
    country: CountryMemory
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp),
        verticalAlignment =
            Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .size(
                    width = 62.dp,
                    height = 68.dp
                )
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .background(WinxWhite),
            contentAlignment =
                Alignment.Center
        ) {

            Icon(
                imageVector =
                    Icons.Filled.Image,
                contentDescription =
                    "${country.country} memory",
                tint = WinxBlue,
                modifier =
                    Modifier.size(28.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(9.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = country.flag,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier =
                        Modifier.width(5.dp)
                )

                Text(
                    text = country.country,
                    fontSize = 13.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color = WinxDarkBlue
                )
            }

            Text(
                text = "● ${country.location}",
                fontSize = 8.sp,
                color = WinxDarkBlue
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Row {

                repeat(3) {

                    Card(
                        modifier = Modifier
                            .padding(
                                end = 4.dp
                            ),
                        shape =
                            RoundedCornerShape(
                                4.dp
                            ),
                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    WinxWhite
                            )
                    ) {

                        Text(
                            text = "Image",
                            modifier =
                                Modifier.padding(
                                    horizontal =
                                        5.dp,
                                    vertical =
                                        3.dp
                                ),
                            fontSize = 7.sp,
                            color =
                                WinxDarkBlue
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.width(75.dp)
        ) {

            Text(
                text =
                    "▣ ${country.venues} Venues",
                fontSize = 7.sp,
                color = WinxDarkBlue
            )

            Text(
                text =
                    "▧ ${country.photos} Photos",
                fontSize = 7.sp,
                color = WinxDarkBlue
            )

            Text(
                text =
                    "▶ ${country.videos} Videos",
                fontSize = 7.sp,
                color = WinxDarkBlue
            )

            Text(
                text =
                    "▣ ${country.dateRange}",
                fontSize = 7.sp,
                color = WinxDarkBlue
            )
        }

        Icon(
            imageVector =
                Icons.Outlined.ChevronRight,
            contentDescription =
                "Open ${country.country}",
            modifier = Modifier.size(18.dp),
            tint = WinxDarkBlue
        )
    }
}


private val LibraryGrey =
    Color(0xFF777777)

private val LibrarySearchGrey =
    Color(0xFFF1F1F1)