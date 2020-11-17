package com.example.nguyenduchuy.util

import androidx.recyclerview.widget.DiffUtil
import com.example.nguyenduchuy.fragment.upcoming.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.overview == newItem.overview
}