package com.practicum.sprint11.presentation.movies

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

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context
) {
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private var lastSearchText: String? = null
    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }
    private val handler = Handler(Looper.getMainLooper())
    private val movies = ArrayList<Movies>()


    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            showLoading()
            moviesInteractor.searchMovies(
                newSearchText.toString(),
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movies>?, errorMessage: String?) {
                        handler.post {
                            view.showProgressBar(false)
                            //progressBar.visibility = View.GONE
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                                view.showRecyclerMovies(true)
                               // recyclerMovies.visibility = View.VISIBLE
                                view.updateMoviesList(movies)

                            }
                            if (errorMessage != null) {
                                showMessage(
                                    context.getString(R.string.something_went_wrong),
                                    errorMessage
                                )
                            } else if (movies.isEmpty()) {
                                showMessage(context.getString(R.string.nothing_found), "")
                            } else {
                                view.showPlaceholderMessage(false)
                            }
                        }
                    }
                })
        }
    }

    fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            view.updateMoviesList(movies)
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
            view.showToast(additionalMessage)
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    fun showLoading() {
        view.showPlaceholderMessage(false)
        view.showProgressBar(true)
        view.showRecyclerMovies(false)
       // recyclerMovies.visibility = View.GONE
       // progressBar.visibility = View.VISIBLE
    }
}