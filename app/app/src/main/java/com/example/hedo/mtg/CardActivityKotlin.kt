package com.example.hedo.mtg

import android.content.Intent
import android.os.Bundle

class CardActivityKotlin: CardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivCard.setOnClickListener {
            val intent = Intent(this, FullscreenCardImageActivity::class.java)
            intent.putExtra(FullscreenCardImageActivity.IMAGE_KEY, card.imageUrl)
            startActivity(intent)
        }
    }

}