package com.practicum.sprint11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.practicum.sprint11.ui.movies.MoviesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonWeather =findViewById<Button>(R.id.buttonWeather)
        val buttonFilms = findViewById<Button>(R.id.buttonFilms)
        val buttonThread = findViewById<Button>(R.id.buttonThread)


        buttonWeather.setOnClickListener {
            val weatherIntent = Intent(this, WeatherActivity::class.java)
            startActivity(weatherIntent)
        }

        buttonFilms.setOnClickListener {
            val movieIntent = Intent(this, MoviesActivity::class.java)
            startActivity(movieIntent)
        }

        buttonThread.setOnClickListener {
            val threadIntent = Intent(this, Thraed::class.java)
            startActivity(threadIntent)
        }
    }
}