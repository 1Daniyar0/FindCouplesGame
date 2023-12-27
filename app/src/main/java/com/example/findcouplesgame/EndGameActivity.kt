package com.example.findcouplesgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findcouplesgame.databinding.EndGamePopupBinding

class EndGameActivity: AppCompatActivity() {

    private lateinit var binding: EndGamePopupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EndGamePopupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val receivedCoins = intent.getIntExtra("coins", 0)
        binding.coinsTv.text = receivedCoins.toString()

        binding.goHomeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tryAgainBtn.setOnClickListener {
            val intent = Intent(this, GameSceneActivity::class.java)
            startActivity(intent)
        }
    }

}