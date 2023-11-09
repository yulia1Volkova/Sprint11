package com.practicum.sprint11.domain.api

import com.practicum.sprint11.domain.models.Movies

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movies>?,errorMessage: String?)
    }
}