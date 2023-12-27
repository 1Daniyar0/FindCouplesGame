package com.example.findcouplesgame

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.findcouplesgame.entiti.Card
import java.lang.NullPointerException

class CardAdapter(private val cardList: List<Card>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)

        holder.itemView.setOnClickListener {
            onCardClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(card: Card) {
            if (card.isFlipped) {
                imageView.setImageResource(card.imageRes)
            } else {
                imageView.setImageResource(R.drawable.question_mark)
            }
        }
    }


    private var gameCompleteListener: GameCompleteListener? = null
    private var lastFlippedPosition: Int? = null
    private var isChecking = false
    private var matchedCardCount = 0

    private fun onCardClicked(position: Int) {
        val card = cardList[position]

        if (!card.isMatched && !card.isFlipped && !isChecking) {
            card.isFlipped = true
            notifyItemChanged(position)

            if (lastFlippedPosition != null) {
                val lastCard = cardList[lastFlippedPosition!!]

                if (lastCard.imageRes != card.imageRes) {
                    isChecking = true

                    Handler(Looper.getMainLooper()).postDelayed({
                        lastCard.isFlipped = false
                        card.isFlipped = false
                        notifyItemChanged(lastFlippedPosition!!)
                        notifyItemChanged(position)
                        isChecking = false
                    }, 500)
                } else {
                    isChecking = true

                    lastCard.isMatched = true
                    card.isMatched = true
                    matchedCardCount += 2
                    Handler(Looper.getMainLooper()).postDelayed({
                        isChecking = false
                    }, 500)
                    if (matchedCardCount == cardList.size) {
                        gameCompleteListener?.onGameComplete()
                    }

                }

                Handler(Looper.getMainLooper()).postDelayed({
                    lastFlippedPosition = null
                }, 500)

            } else {
                lastFlippedPosition = position
            }
        }
    }

    fun setGameCompleteListener(listener: GameCompleteListener) {
        gameCompleteListener = listener
    }
}