package com.example.soal2.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soal2.model.Character

private val CardBackground = Color(0xFF171A29)
private val CardBorder = Color(0xFF303448)
private val White = Color(0xFFF4F5FC)
private val Muted = Color(0xFF9298AD)
private val Yellow = Color(0xFFFFB900)
private val HpRed = Color(0xFFFF3154)
private val MpBlue = Color(0xFF168FE8)

@Composable
fun CharacterCard(character: Character) {
    val isInSquad = character.name == "Elaria"

    val classColor = when (character.className) {
        "Paladin" -> Color(0xFFFFB900)
        "Cleric" -> Color(0xFF16D99A)
        "Rogue" -> Color(0xFFE45BFA)
        "Archmage" -> Color(0xFF00D9F5)
        "Berserker" -> Color(0xFFFF6D35)
        else -> Muted
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(13.dp))
            .background(CardBackground)
            .border(
                width = if (isInSquad) 1.dp else 0.7.dp,
                color = if (isInSquad) Yellow else CardBorder,
                shape = RoundedCornerShape(13.dp)
            )
            .padding(
                start = 9.dp,
                end = 9.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        // RARITY, CLASS, AND CP
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "MYTHIC",
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFF42132B))
                    .border(
                        0.5.dp,
                        Color(0xFFFF3154),
                        RoundedCornerShape(3.dp)
                    )
                    .padding(
                        horizontal = 4.dp,
                        vertical = 2.dp
                    ),
                color = Color(0xFFFF4770),
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = when (character.name) {
                    "Alistair" -> "High Templar"
                    "Theresa" -> "Divine Oracle"
                    "Vespera" -> "Shadowblade"
                    "Elaria" -> "Archmage"
                    "Ragnar" -> "Berserker"
                    else -> character.className
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(classColor.copy(alpha = 0.13f))
                    .padding(
                        horizontal = 4.dp,
                        vertical = 2.dp
                    ),
                color = classColor,
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "⚔ ${character.cp} CP",
                color = Yellow,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )

            if (isInSquad) {
                Spacer(Modifier.width(4.dp))

                Text(
                    text = "IN SQUAD",
                    modifier = Modifier
                        .border(
                            0.5.dp,
                            Yellow,
                            RoundedCornerShape(3.dp)
                        )
                        .padding(
                            horizontal = 3.dp,
                            vertical = 2.dp
                        ),
                    color = Yellow,
                    fontSize = 6.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        // CHARACTER IMAGE AND STATS
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 55.dp, height = 65.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0D1020))
                    .border(
                        1.dp,
                        classColor,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = character.image),
                    contentDescription = character.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Lvl ${character.level}",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(
                            RoundedCornerShape(
                                topStart = 5.dp
                            )
                        )
                        .background(Color(0xE6000000))
                        .padding(
                            horizontal = 4.dp,
                            vertical = 2.dp
                        ),
                    color = White,
                    fontSize = 7.sp,
                    lineHeight = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = character.name,
                        color = White,
                        fontSize = 13.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "⌄",
                        color = Muted,
                        fontSize = 15.sp
                    )
                }

                Spacer(Modifier.height(4.dp))

                StatBar(
                    label = "HP",
                    current = character.hp,
                    maximum = character.maxHp,
                    color = HpRed
                )

                Spacer(Modifier.height(4.dp))

                StatBar(
                    label = "MP",
                    current = character.mp,
                    maximum = character.maxMp,
                    color = MpBlue
                )
            }
        }
    }
}

@Composable
private fun StatBar(
    label: String,
    current: Int,
    maximum: Int,
    color: Color
) {
    val progress = if (maximum > 0) {
        (current.toFloat() / maximum.toFloat())
            .coerceIn(0f, 1f)
    } else {
        0f
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.width(20.dp),
            color = Muted,
            fontSize = 8.sp,
            lineHeight = 10.sp
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xFF090D19))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(5.dp))
                    .background(color)
            )
        }

        Spacer(Modifier.width(5.dp))

        Text(
            text = "$current / $maximum",
            modifier = Modifier.width(55.dp),
            color = Muted,
            fontSize = 7.sp,
            lineHeight = 9.sp,
            maxLines = 1
        )
    }
}