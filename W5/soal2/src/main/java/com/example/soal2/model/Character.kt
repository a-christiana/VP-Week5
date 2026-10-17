package com.example.soal2.model

data class Character(
    val id: Int,
    val name: String,
    val className: String,
    val image: Int,
    val rarity: String = "MYTHIC",
    val level: Int,
    val cp: Int,
    val hp: Int,
    val maxHp: Int,
    val mp: Int,
    val maxMp: Int
)
