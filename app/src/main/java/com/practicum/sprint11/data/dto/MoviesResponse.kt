package com.practicum.sprint11.data.dto

import com.practicum.sprint11.domain.models.Movies

class MoviesResponse (val searchType: String,
                      val expression: String,
                      val results: List<MovieDto>) : Response() {
}