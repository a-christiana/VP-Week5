package com.example.w5.model
data class Car(
    val id: Int,
    val name: String,
    val brand: String,
    val year: Int,
    val image: String,
    val isFavorite: Boolean = false,
    val mileage: Int = 0,
    val transmission: String = "Auto",
    val fuelType: String = "Petrol"
)