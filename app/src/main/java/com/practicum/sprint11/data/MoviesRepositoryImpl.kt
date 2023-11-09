package com.practicum.sprint11.data

import com.practicum.sprint11.data.dto.MovieRequest
import com.practicum.sprint11.data.dto.MoviesResponse
import com.practicum.sprint11.domain.Resource
import com.practicum.sprint11.domain.repository.MoviesRepository
import com.practicum.sprint11.domain.models.Movies

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {


    override fun searchMovies(expression: String): Resource<List<Movies>> {
        val response = networkClient.doRequest(MovieRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesResponse).results.map {
                    Movies(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
/*    override fun searchMovies(expression: String): Resource<List<Movies>> {
        TODO("Not yet implemented")
    }*/
}