package com.practicum.sprint11.ui.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.sprint11.util.Creator
import com.practicum.sprint11.R
import com.practicum.sprint11.domain.models.Movies
import com.practicum.sprint11.presentation.movies.MoviesView
import com.practicum.sprint11.ui.poster.poster

class MoviesActivity : AppCompatActivity(), MoviesView {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private var textWatcher: TextWatcher? = null
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, poster::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchPresenter = Creator.provideMoviesSearchPresenter(
        moviesView = this,
        context = this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryMoviesInput)
        progressBar = findViewById(R.id.progressBar)
        recyclerMovies = findViewById(R.id.moviesList)

        recyclerMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerMovies.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesSearchPresenter.searchDebounce(
                    changedText = s?.toString() ?: ""
                )
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        textWatcher?.let { queryInput.addTextChangedListener(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesSearchPresenter.onDestroy()
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    override fun showPlaceholderMessage(isVisible: Boolean) {
        placeholderMessage.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showRecyclerMovies(isVisible: Boolean) {
        recyclerMovies.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun changePlaceholderText(newPlaceholderText: String) {
        placeholderMessage.text = newPlaceholderText
    }

    override fun updateMoviesList(newMoviesList: List<Movies>) {
        adapter.movies.clear()
        adapter.movies.addAll(newMoviesList)
        adapter.notifyDataSetChanged()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }


}

