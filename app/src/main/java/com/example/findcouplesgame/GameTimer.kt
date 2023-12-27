package com.example.findcouplesgame

import android.os.Handler

class GameTimer(private val timerUpdateListener: TimerUpdateListener) {
    private var currentValue = 0
    private var isTimerRunning = false
    private var handler: Handler? = null
    private var runnable: Runnable? = null



    fun startTimer() {
        isTimerRunning = true
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                if (isTimerRunning) {
                    currentValue++
                    timerUpdateListener.onTimerUpdate(currentValue)
                    handler?.postDelayed(this, 1000)
                }
            }
        }
        handler?.postDelayed(runnable!!, 1000)
    }

    fun stopTimer() {
        isTimerRunning = false
        handler?.removeCallbacks(runnable!!)
    }


}