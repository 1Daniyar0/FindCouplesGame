package com.example.findcouplesgame

interface GameCompleteListener {
    fun onGameComplete()
}

interface TimerUpdateListener {
    fun onTimerUpdate(value: Int)
}