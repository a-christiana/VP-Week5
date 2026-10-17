package com.example.w5.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.w5.model.Car

class CarViewModel : ViewModel() {

    val cars = mutableStateListOf(
        Car(
            id = 1,
            name = "Tiggo 9 CSH",
            brand = "Chery",
            year = 2024,
            image = "tiggo",
            mileage = 5000,
            transmission = "Auto",
            fuelType = "Hybrid"
        ),
        Car(
            id = 2,
            name = "Honda CR-V",
            brand = "Honda",
            year = 2013,
            image = "crv",
            mileage = 15200,
            transmission = "Auto",
            fuelType = "Petrol"
        ),
        Car(
            id = 3,
            name = "Toyota Yaris Cross",
            brand = "Toyota",
            year = 2024,
            image = "yariscross",
            mileage = 30500,
            transmission = "Auto",
            fuelType = "Hybrid"
        ),
        Car(
            id = 4,
            name = "Daihatsu Xenia",
            brand = "Daihatsu",
            year = 2022,
            image = "xenia",
            mileage = 28000,
            transmission = "Manual",
            fuelType = "Petrol"
        ),
        Car(
            id = 5,
            name = "Toyota Yaris",
            brand = "Toyota",
            year = 2021,
            image = "yaris",
            mileage = 52400,
            transmission = "Auto",
            fuelType = "Petrol"
        ),
        Car(
            id = 6,
            name = "Daihatsu Ayla",
            brand = "Daihatsu",
            year = 2023,
            image = "ayla",
            mileage = 18000,
            transmission = "Manual",
            fuelType = "Petrol"
        ),
        Car(
            id = 7,
            name = "Honda HR-V",
            brand = "Honda",
            year = 2023,
            image = "hrv",
            mileage = 22000,
            transmission = "Auto",
            fuelType = "Petrol"
        ),
        Car(
            id = 8,
            name = "MG4 EV",
            brand = "MG",
            year = 2024,
            image = "mg4",
            mileage = 12000,
            transmission = "Auto",
            fuelType = "Electric"
        ),
        Car(
            id = 9,
            name = "Jaecoo J5",
            brand = "Jaecoo",
            year = 2025,
            image = "j5",
            mileage = 8000,
            transmission = "Auto",
            fuelType = "Petrol"
        )
    )

    fun toggleFavorite(carId: Int) {
        val index = cars.indexOfFirst { it.id == carId }

        if (index != -1) {
            cars[index] = cars[index].copy(
                isFavorite = !cars[index].isFavorite
            )
        }
    }
}
