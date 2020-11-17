package com.example.nguyenduchuy.fragment.upcoming.model

data class Movie(
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var poster_path: String,
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var title: String,
    var vote_average: Double,
    var overview: String,
    var name: String,
    var release_date: String) {
}