package com.example.findcouplesgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.findcouplesgame.databinding.GameSceneBinding
import com.example.findcouplesgame.entiti.Card

class GameSceneActivity:  AppCompatActivity(),TimerUpdateListener,GameCompleteListener  {
    private lateinit var binding: GameSceneBinding
    private lateinit var cardAdapter: CardAdapter
    private lateinit var cards: MutableList<Card>
    private var coinsValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameSceneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRv()
        val gameTimer = GameTimer(this)
        gameTimer.startTimer()



    }

    private fun setupRv(){
        cards = mutableListOf()

        for (i in 0 until 10){
            cards.add(Card(i,false,false,R.drawable.diamond))
            cards.add(Card(i,false,false,R.drawable.green_stone))
        }
        cards.shuffle()

        cardAdapter = CardAdapter(cards)
        cardAdapter.setGameCompleteListener(this)
        val mLayoutManager = GridLayoutManager(this, 4)
        binding.gameRv.layoutManager = mLayoutManager
        binding.gameRv.adapter = cardAdapter
    }

    override fun onTimerUpdate(value: Int) {
        runOnUiThread {
            val minutes = value / 60
            val seconds = value % 60

            val formattedTime = String.format("%02d:%02d", minutes, seconds)
            binding.timerTv.text = formattedTime

            if (value <= 20) {
                coinsValue = 100
            } else {
                val additionalSeconds = value - 20
                coinsValue = 100 - (additionalSeconds * 5)
                coinsValue = maxOf(coinsValue, 10)
            }

            binding.coinValueTv.text = coinsValue.toString()
        }
    }
    override fun onGameComplete() {
        val intent = Intent(this, EndGameActivity::class.java)
        intent.putExtra("coins",coinsValue)
        startActivity(intent)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val coins = sharedPreferences.getInt("coins", 0) + coinsValue
        editor.putInt("coins", coins)
        editor.apply()
    }




}