package com.example.nguyenduchuy.api

import com.example.nguyenduchuy.fragment.BaseResponse
import com.example.nguyenduchuy.fragment.detailmovie.model.Detail
import com.example.nguyenduchuy.fragment.detailmovie.model.Video
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.ApiConstants
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiConstants.API_UPCOMING)
    fun upcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Movie>>

    @GET(ApiConstants.API_TOP_RATE)
    fun topRate(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Movie>>

    @GET(ApiConstants.API_POPULAR)
    fun popular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Movie>>

    @GET(ApiConstants.API_NOW_PLAYING)
    fun nowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Movie>>

    @GET(ApiConstants.API_DETAIL)
    fun detailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Detail>

    @GET(ApiConstants.API_VIDEOS)
    fun videos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Video>>

    @GET(ApiConstants.API_SIMILAR_VIDEO)
    fun similarVideo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Detail>>

    @GET(ApiConstants.API_RECOMMENDATIONS)
    fun recommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<BaseResponse<Detail>>

    @GET(ApiConstants.API_SEARCH)
    fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Observable<BaseResponse<Movie>>

}