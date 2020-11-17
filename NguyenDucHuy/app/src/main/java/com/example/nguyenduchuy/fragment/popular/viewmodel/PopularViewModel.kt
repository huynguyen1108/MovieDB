package com.example.nguyenduchuy.fragment.popular.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularViewModel : ViewModel() {
    private val _mListMoviePopular: MutableLiveData<List<Movie>?> = MutableLiveData()
    val mListMoviePopular: LiveData<List<Movie>?> = _mListMoviePopular

    @SuppressLint("CheckResult")
    fun getListPopular(apiKey: String){
        Utils.createRetrofit().popular(apiKey, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _mListMoviePopular.postValue(movie.results)
            }, { _ ->
                _mListMoviePopular.postValue(null)
            })
    }
}