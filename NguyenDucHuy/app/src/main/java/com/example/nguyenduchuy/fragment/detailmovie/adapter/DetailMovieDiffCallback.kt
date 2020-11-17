package com.example.nguyenduchuy.fragment.detailmovie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nguyenduchuy.fragment.detailmovie.model.Detail
import com.example.nguyenduchuy.fragment.upcoming.model.Movie

class DetailMovieDiffCallback : DiffUtil.ItemCallback<Detail>(){
    override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean = oldItem.overview == newItem.overview
}