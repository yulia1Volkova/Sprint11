package com.practicum.sprint11

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApi {
        @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
        fun getMovies (@Path("expression") expression: String): Call<MoviesResponse>
    }
