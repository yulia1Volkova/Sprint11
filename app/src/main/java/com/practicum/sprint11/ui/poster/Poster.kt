package com.practicum.sprint11.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.practicum.sprint11.R
import com.practicum.sprint11.presentation.poster.PosterView
import com.practicum.sprint11.util.Creator
import com.practicum.sprint11.util.Creator.providePosterPresenter

class poster : AppCompatActivity(), PosterView {
    private lateinit var poster: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)
        val url = intent.extras?.getString("poster", "")
        val posterPresenter = Creator.providePosterPresenter(this, url?:"")
        posterPresenter.onCreate()
    }

    override fun setImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }


}

