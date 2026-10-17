package com.example.w5.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.w5.R
import com.example.w5.model.Car
import java.util.Locale

private val Coral = Color(0xFFFF684D)
private val MutedText = Color(0xFF7D858D)
private val DarkText = Color(0xFF202B38)

@Composable
fun CarCard(
    car: Car,
    onFavoriteClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 112.dp, height = 88.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(
                        id = when (car.image) {
                            "ayla" -> R.drawable.ayla
                            "crv" -> R.drawable.crv
                            "hrv" -> R.drawable.hrv
                            "j5" -> R.drawable.j5
                            "mg4" -> R.drawable.mg4
                            "tiggo" -> R.drawable.tiggo
                            "xenia" -> R.drawable.xenia
                            "yaris" -> R.drawable.yaris
                            "yariscross" -> R.drawable.yariscross
                            else -> R.drawable.ayla
                        }
                    ),
                    contentDescription = car.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when (car.fuelType.lowercase()) {
                                "petrol" -> Color(0xFFFFE5C2)
                                "hybrid" -> Color(0xFFE2E9FF)
                                "electric" -> Color(0xFFDDF4E4)
                                else -> Color(0xFFF0F0F0)
                            }
                        )
                        .padding(
                            horizontal = 5.dp,
                            vertical = 3.dp
                        )
                ) {
                    Text(
                        text = car.fuelType.uppercase(),
                        color = when (car.fuelType.lowercase()) {
                            "petrol" -> Color(0xFFB56A16)
                            "hybrid" -> Color(0xFF435CB5)
                            "electric" -> Color(0xFF25834A)
                            else -> Color(0xFF666666)
                        },
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = car.name,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        maxLines = 1
                    )

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (car.isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Filled.FavoriteBorder
                            },
                            contentDescription = if (car.isFavorite) {
                                "Hapus dari favorit"
                            } else {
                                "Tambahkan ke favorit"
                            },
                            tint = if (car.isFavorite) {
                                Coral
                            } else {
                                Color(0xFFBBC1C7)
                            },
                            modifier = Modifier.size(19.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = car.transmission,
                    color = MutedText,
                    fontSize = 9.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xFFF0F0F0))
                        .padding(
                            horizontal = 6.dp,
                            vertical = 3.dp
                        )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Coral)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = car.year.toString(),
                        color = DarkText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "%,d km".format(
                            Locale.US,
                            car.mileage
                        ),
                        color = MutedText,
                        fontSize = 9.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
