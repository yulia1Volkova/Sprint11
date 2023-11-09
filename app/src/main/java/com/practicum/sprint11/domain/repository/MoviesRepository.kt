package com.practicum.sprint11.domain.repository

import com.practicum.sprint11.domain.Resource
import com.practicum.sprint11.domain.models.Movies

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movies>>
}