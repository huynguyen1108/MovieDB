package com.example.nguyenduchuy.fragment.detailmovie.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.detailmovie.model.DataZipMovie
import com.example.nguyenduchuy.fragment.detailmovie.model.Detail
import com.example.nguyenduchuy.fragment.detailmovie.model.Video
import com.example.nguyenduchuy.util.Utils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailMovieViewModel : ViewModel() {
    val detail: MutableLiveData<Detail> = MutableLiveData()
    val video: MutableLiveData<List<Video>> = MutableLiveData()
    val similarVideo: MutableLiveData<List<Detail>> = MutableLiveData()
    val recommendations: MutableLiveData<List<Detail>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getDetail(movieId: Int, apiKey: String) {
        Single.zip(
            Utils.createRetrofit().detailMovie(movieId, apiKey, "en-US"),
            Utils.createRetrofit().videos(movieId, apiKey, "en-US"),
            Utils.createRetrofit().similarVideo(movieId, apiKey, "en-US"),
            Utils.createRetrofit().recommendations(movieId, apiKey, "en-US"),
            { t1, t2, t3, t4 ->
                DataZipMovie(t1, t2, t3, t4)
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1, t2 ->
                run {
                    detail.postValue(t1.detail)
                    video.postValue(t1.video.results)
                    similarVideo.postValue(t1.similarVideo.results)
                    recommendations.postValue(t1.recommendations.results)
                }
            }
    }
}