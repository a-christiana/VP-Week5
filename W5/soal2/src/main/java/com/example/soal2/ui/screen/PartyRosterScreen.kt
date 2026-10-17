package com.example.soal2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soal2.ui.widgets.CharacterCard
import com.example.soal2.viewmodel.CharacterViewModel
import androidx.compose.foundation.layout.size
import com.example.soal2.R

private val Background = Color(0xFF090B14)
private val Panel = Color(0xFF171A29)
private val Yellow = Color(0xFFFFB900)
private val Muted = Color(0xFF9298AD)
private val White = Color(0xFFF4F5FC)

@Composable
fun PartyRosterScreen(
    characterViewModel: CharacterViewModel = viewModel()
) {
    val selectedFilter by
    characterViewModel.selectedFilter.collectAsState()

    val visibleCharacters =
        if (selectedFilter == "All") {
            characterViewModel.characters
        } else {
            characterViewModel.characters.filter {
                it.className == selectedFilter
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Party Roster",
                    color = White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "Guild Vanguard • 14 Heroes Available",
                    color = Muted,
                    fontSize = 10.sp
                )
            }

            Text(
                text = "★ Expedition",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF34280B))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                color = Yellow,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Squad panel
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Panel)
                .padding(10.dp)
        ) {
            Text(
                text = "EXPEDITION SQUAD (3/4)",
                color = Muted,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val characterImages = listOf(
                    R.drawable.berserker,
                    R.drawable.archmage,
                    R.drawable.paladin
                )
                characterImages.forEach { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Character image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Background),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", color = Muted, fontSize = 20.sp)
                }

                Spacer(Modifier.weight(1f))

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "⚔ 14750 CP",
                        color = Yellow,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(5.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(
                            horizontal = 12.dp,
                            vertical = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Yellow,
                            contentColor = Background
                        )
                    ) {
                        Text(
                            text = "Deploy ⚡",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // Search bar, visual only
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Panel)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("⌕", color = Muted, fontSize = 20.sp)

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Search by hero name, class, or rarity...",
                color = Muted,
                fontSize = 10.sp
            )
        }

        Spacer(Modifier.height(10.dp))

        // Horizontal character filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            characterViewModel.filters.forEach { filter ->
                val isSelected = selectedFilter == filter

                Text(
                    text = if (filter == "All") {
                        "All (${characterViewModel.characters.size})"
                    } else {
                        filter
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) Yellow else Panel
                        )
                        .clickable {
                            characterViewModel.selectFilter(filter)
                        }
                        .padding(
                            horizontal = 10.dp,
                            vertical = 7.dp
                        ),
                    color = if (isSelected) Background else Muted,
                    fontSize = 9.sp,
                    fontWeight = if (isSelected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                )
            }

            Text(
                text = "Sort Power",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF34280B))
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                color = Yellow,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(10.dp))

        // Scrollable character cards
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = 2.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            items(
                items = visibleCharacters,
                key = { character -> character.id }
            ) { character ->
                CharacterCard(character = character)
            }
        }
    }
}









@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PartyRosterPreview() {
    MaterialTheme {
        PartyRosterScreen(
            characterViewModel = viewModel()
        )
    }
}