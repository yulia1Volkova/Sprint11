package com.practicum.sprint11

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MovieViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_list_item, parent, false)) {
    var name: TextView = itemView.findViewById(R.id.moviesList)

    fun bind(location: Movies) {
        val movieCover: ImageView = itemView.findViewById(R.id.imageMovies)
        val movieName: TextView = itemView.findViewById(R.id.movieName)
        val movieDescription: TextView = itemView.findViewById(R.id.movieDescription)


        fun bind(model: Movies) {
            movieName.text = model.title
            movieDescription.text = model.description
            Glide.with(itemView)
                .load(model.image)
                .centerCrop()
                .transform(RoundedCorners(4))
                .into(movieCover)

        }
    }
}