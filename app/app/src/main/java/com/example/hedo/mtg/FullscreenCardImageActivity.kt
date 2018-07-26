package com.example.hedo.mtg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hedo.mtg.utils.loadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fullscreen_card_image.*


class FullscreenCardImageActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_KEY = "IMAGE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_card_image)

        val url = intent.getStringExtra(IMAGE_KEY)
        imageView.loadImage(url)
    }
}
