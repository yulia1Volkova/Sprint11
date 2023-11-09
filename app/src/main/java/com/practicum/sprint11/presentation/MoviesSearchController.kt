package com.practicum.sprint11.presentation

import android.app.Activity
import android.content.Context
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
import com.practicum.sprint11.domain.api.MoviesInteractor
import com.practicum.sprint11.domain.models.Movies
import com.practicum.sprint11.ui.movies.MoviesAdapter

class MoviesSearchController(
    private val activity: Activity,
    private val adapter: MoviesAdapter
) {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val moviesInteractor = Creator.provideMoviesInteractor(activity)

    private val searchRunnable = Runnable { searchRequest() }
    private val handler = Handler(Looper.getMainLooper())
    private val movies = ArrayList<Movies>()

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var progressBar: ProgressBar

    fun onCreate() {
        placeholderMessage = activity.findViewById(R.id.placeholderMessage)
        queryInput = activity.findViewById(R.id.queryMoviesInput)
        progressBar = activity.findViewById(R.id.progressBar)
        recyclerMovies = activity.findViewById(R.id.moviesList)


        adapter.movies = movies

        recyclerMovies.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerMovies.adapter = adapter

        queryInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchDebounce()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    private fun searchRequest() {
        if (queryInput.text.isNotEmpty()) {
            showLoading()
            moviesInteractor.searchMovies(
                queryInput.text.toString(),
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movies>?, errorMessage: String?) {
                        handler.post {
                            progressBar.visibility = View.GONE
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                                recyclerMovies.visibility = View.VISIBLE
                                adapter.notifyDataSetChanged()

                            }
                            if (errorMessage != null) {
                                showMessage(
                                    activity.getString(R.string.something_went_wrong),
                                    errorMessage
                                )
                            } else if (movies.isEmpty()) {
                                showMessage(activity.getString(R.string.nothing_found), "")
                            } else {
                                placeholderMessage.visibility = View.GONE
                            }
                        }
                    }
                })
        }
    }

    fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            movies.clear()
            adapter.notifyDataSetChanged()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(
                    activity.applicationContext,
                    additionalMessage,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }

    private fun searchDebounce() {
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    fun showLoading() {
        placeholderMessage.visibility = View.GONE
        recyclerMovies.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }
}