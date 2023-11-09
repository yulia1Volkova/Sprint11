package com.practicum.sprint11.ui.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.practicum.sprint11.util.Creator
import com.practicum.sprint11.R
import com.practicum.sprint11.ui.poster.poster

class MoviesActivity : AppCompatActivity() {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, poster::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchController = Creator.provideMoviesSearchController(this, adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        moviesSearchController.onCreate()
    }
    override fun onDestroy() {
        super.onDestroy()
        moviesSearchController.onDestroy()
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

}

