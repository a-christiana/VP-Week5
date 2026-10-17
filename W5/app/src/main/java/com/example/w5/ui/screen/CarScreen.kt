package com.example.w5.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.w5.ui.widgets.CarCard
import com.example.w5.viewmodel.CarViewModel

private val PageCream = Color(0xFFF6F2EE)
private val AccentCoral = Color(0xFFFF684D)
private val DarkText = Color(0xFF202B38)
private val MutedText = Color(0xFF7D858D)

@Composable
fun CarScreen(
    carViewModel: CarViewModel = viewModel()
) {
    val favoriteCount = carViewModel.cars.count {
        it.isFavorite
    }

    Scaffold(
        containerColor = PageCream
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageCream)
                .padding(innerPadding)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .width(24.dp)
                            .height(3.dp)
                            .background(
                                AccentCoral,
                                RoundedCornerShape(50)
                            )
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "On Sale",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkText
                    )

                    Text(
                        text = "Find your perfect ride",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Row(
                    modifier = Modifier
                        .background(
                            Color(0xFFFFE6DE),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Mobil favorit",
                        tint = AccentCoral
                    )

                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )

                    Text(
                        text = "$favoriteCount saved",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCoral
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = carViewModel.cars,
                    key = { car -> car.id }
                ) { car ->
                    CarCard(
                        car = car,
                        onFavoriteClick = {
                            carViewModel.toggleFavorite(car.id)
                        }
                    )
                }
            }
        }
    }
}







@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarScreenPreview() {
    MaterialTheme {
        CarScreen()
    }
}