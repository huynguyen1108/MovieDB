package com.example.nguyenduchuy.fragment.nowplaying.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NowPlayingViewModel : ViewModel() {
    private val _mListMovieNowPlaying: MutableLiveData<List<Movie>?> = MutableLiveData()
    val mListMovieNowPlaying: LiveData<List<Movie>?> = _mListMovieNowPlaying

    @SuppressLint("CheckResult")
    fun getListNowPlaying(apiKey: String){
        Utils.createRetrofit().nowPlaying(apiKey, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _mListMovieNowPlaying.postValue(movie.results)
            }, { _ ->
                _mListMovieNowPlaying.postValue(null)
            })
    }
}