package com.practicum.sprint11.presentation.poster
import android.content.Context
import com.bumptech.glide.Glide

class PosterPresenter(private val view: PosterView, val url:String){

fun onCreate(){
    view.setImage(url)
}
}