package com.example.findcouplesgame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findcouplesgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val coins = sharedPreferences.getInt("coins", 0)

        binding.coinsTv.text = coins.toString()
        binding.playBtn.setOnClickListener {
            val intent = Intent(this, GameSceneActivity::class.java)
            startActivity(intent)
        }


    }
}