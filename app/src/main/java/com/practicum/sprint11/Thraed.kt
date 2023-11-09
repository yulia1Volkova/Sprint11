package com.practicum.sprint11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class Thraed : AppCompatActivity() {
    companion object {

        private const val REFRESH_TIMER = 1_000L
    }

    private var mainThreadHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thraed)

        val button = findViewById<Button>(R.id.buttonThread)
        val editText = findViewById<EditText>(R.id.myTextView)
        val textViewTimer = findViewById<TextView>(R.id.timer)

    }
}