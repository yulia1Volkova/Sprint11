package com.practicum.sprint11.presentation.movies

import com.practicum.sprint11.domain.models.Movies

interface MoviesView {
    fun showPlaceholderMessage(isVisible: Boolean)

    fun showRecyclerMovies(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)
    fun updateMoviesList(newMoviesList: List<Movies>)

    fun showToast(additionalMessage:String)
}