package com.practicum.sprint11.ui.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.sprint11.domain.models.Movies

class MoviesAdapter (val clickListener: MovieClickListener
) : RecyclerView.Adapter<MovieViewHolder> () {
    var movies = ArrayList<Movies>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movies[position]) }
    }

    override fun getItemCount() = movies.size

    fun interface MovieClickListener {
        fun onMovieClick(movie: Movies)
    }
}