package com.example.nguyenduchuy.fragment.detailmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nguyenduchuy.databinding.ItemMovieDetailBinding
import com.example.nguyenduchuy.fragment.detailmovie.model.Detail

class MovieDetailListAdapter(val onClick: (Detail) -> Unit) : ListAdapter<Detail, MovieDetailListAdapter.ViewHolder>(DetailMovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieDetailBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), this)
    }

    class ViewHolder(private val dataBinding: ItemMovieDetailBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(item: Detail, adapterDetail: MovieDetailListAdapter){
            dataBinding.adapter = adapterDetail
            dataBinding.item = item
        }
    }

    fun setOnClick(item: Detail){
        onClick(item)
    }
}