package com.example.nguyenduchuy.fragment.upcoming.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nguyenduchuy.databinding.ItemMovieBinding
import com.example.nguyenduchuy.fragment.detailmovie.adapter.MovieDetailListAdapter
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.MovieDiffCallback

class MovieListAdapter(val onClick: (Movie) -> Unit) : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), this)
    }

    class ViewHolder(private val dataBinding: ItemMovieBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(item: Movie, adapter: MovieListAdapter){
            dataBinding.adapter = adapter
            dataBinding.item = item
        }
    }

    fun setOnClick(item: Movie){
        onClick(item)
    }
}