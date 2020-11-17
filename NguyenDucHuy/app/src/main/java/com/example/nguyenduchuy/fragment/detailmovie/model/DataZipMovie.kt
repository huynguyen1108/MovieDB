package com.example.nguyenduchuy.fragment.detailmovie.model

import com.example.nguyenduchuy.fragment.BaseResponse

data class DataZipMovie(
    val detail: Detail,
    val video: BaseResponse<Video>,
    val similarVideo: BaseResponse<Detail>,
    val recommendations: BaseResponse<Detail>) {
}