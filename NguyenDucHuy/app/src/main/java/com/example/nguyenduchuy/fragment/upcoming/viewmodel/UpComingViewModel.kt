package com.example.nguyenduchuy.fragment.upcoming.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpComingViewModel : ViewModel() {
    private val _mListMovieUpcoming: MutableLiveData<List<Movie>?> = MutableLiveData()
    val mListMovieUpcoming: LiveData<List<Movie>?> = _mListMovieUpcoming

    @SuppressLint("CheckResult")
    fun getListUpcoming(apiKey: String){
        Utils.createRetrofit().upcoming(apiKey, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _mListMovieUpcoming.postValue(movie.results)
            }, { _ ->
                _mListMovieUpcoming.postValue(null)
            })
    }
}