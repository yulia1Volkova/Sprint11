package com.practicum.sprint11

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter (
) : RecyclerView.Adapter<MovieViewHolder> () {
    var movies = ArrayList<Movies>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder=MovieViewHolder(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}