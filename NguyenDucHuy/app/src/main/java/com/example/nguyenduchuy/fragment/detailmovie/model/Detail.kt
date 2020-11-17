package com.example.nguyenduchuy.fragment.detailmovie.model

data class Detail(
    val budget: Int,
    val id: Int,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val overview: String,
    val poster_path: String,
    val tagline: String,
    val release_date: String) {
}