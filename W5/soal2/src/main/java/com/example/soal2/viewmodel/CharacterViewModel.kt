package com.example.soal2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soal2.R
import com.example.soal2.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel : ViewModel() {

    val characters = listOf(
        Character(
            id = 1,
            name = "Alistair",
            className = "Paladin",
            image = R.drawable.paladin,
            level = 50,
            cp = 5600,
            hp = 1650,
            maxHp = 1800,
            mp = 650,
            maxMp = 800
        ),
        Character(
            id = 2,
            name = "Theresa",
            className = "Cleric",
            image = R.drawable.cleric,
            level = 49,
            cp = 5480,
            hp = 1100,
            maxHp = 1200,
            mp = 2100,
            maxMp = 2400
        ),
        Character(
            id = 3,
            name = "Vespera",
            className = "Rogue",
            image = R.drawable.rogue,
            level = 48,
            cp = 5350,
            hp = 890,
            maxHp = 950,
            mp = 410,
            maxMp = 600
        ),
        Character(
            id = 4,
            name = "Elaria",
            className = "Archmage",
            image = R.drawable.archmage,
            level = 46,
            cp = 5120,
            hp = 450,
            maxHp = 600,
            mp = 1850,
            maxMp = 2000
        ),
        Character(
            id = 5,
            name = "Ragnar",
            className = "Berserker",
            image = R.drawable.berserker,
            level = 45,
            cp = 4980,
            hp = 1900,
            maxHp = 2200,
            mp = 350,
            maxMp = 500
        )
    )

    val filters = listOf(
        "All",
        "Berserker",
        "Archmage",
        "Cleric",
        "Paladin",
        "Rogue"
    )

    private val _selectedFilter = MutableStateFlow("All")

    val selectedFilter: StateFlow<String> =
        _selectedFilter.asStateFlow()

    fun selectFilter(filter: String) {
        _selectedFilter.value = filter
    }
}
