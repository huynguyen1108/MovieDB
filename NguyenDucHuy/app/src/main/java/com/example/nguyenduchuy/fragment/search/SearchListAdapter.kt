package com.example.nguyenduchuy.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nguyenduchuy.databinding.ItemMovieBinding
import com.example.nguyenduchuy.databinding.ItemSearchBinding
import com.example.nguyenduchuy.fragment.detailmovie.adapter.MovieDetailListAdapter
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.MovieDiffCallback

class SearchListAdapter() : ListAdapter<Movie, SearchListAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val dataBinding: ItemSearchBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(item: Movie){
            dataBinding.item = item
        }
    }
}