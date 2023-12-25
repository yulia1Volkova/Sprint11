package com.practicum.sprint11.util

import android.app.Activity
import android.content.Context
import com.practicum.sprint11.data.MoviesRepositoryImpl
import com.practicum.sprint11.data.network.RetrofitNetworkClient
import com.practicum.sprint11.domain.api.MoviesInteractor
import com.practicum.sprint11.domain.repository.MoviesRepository
import com.practicum.sprint11.domain.impl.MoviesInteractorImpl
import com.practicum.sprint11.presentation.movies.MoviesSearchPresenter
import com.practicum.sprint11.presentation.movies.MoviesView
import com.practicum.sprint11.presentation.poster.PosterPresenter
import com.practicum.sprint11.presentation.poster.PosterView
import com.practicum.sprint11.ui.movies.MoviesAdapter


object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(view = moviesView, context=context)
    }

    fun providePosterPresenter(posterView: PosterView, url:String): PosterPresenter {
        return PosterPresenter(view = posterView, url=url)
    }
}