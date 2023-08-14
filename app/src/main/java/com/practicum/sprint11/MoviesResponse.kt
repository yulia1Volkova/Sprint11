package com.practicum.sprint11

class MoviesResponse (val searchType: String,
                      val expression: String,
                      val results: List<Movies>) {
}