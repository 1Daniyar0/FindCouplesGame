package com.example.findcouplesgame.entiti

data class Card(
    val id: Int,
    var isMatched: Boolean = false,
    var isFlipped: Boolean = false,
    val imageRes: Int
)