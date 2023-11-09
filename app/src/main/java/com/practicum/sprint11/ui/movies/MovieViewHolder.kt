package com.practicum.sprint11.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.sprint11.R
import com.practicum.sprint11.domain.models.Movies

class MovieViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_list_item, parent, false)) {


        private val movieCover: ImageView = itemView.findViewById(R.id.imageMovies)
        private val movieName: TextView = itemView.findViewById(R.id.movieName)
        private val movieDescription: TextView = itemView.findViewById(R.id.movieDescription)


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
