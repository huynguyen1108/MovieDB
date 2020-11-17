package com.example.nguyenduchuy.fragment.toprated.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel : ViewModel() {
    private val _mListMovieTopRate: MutableLiveData<List<Movie>?> = MutableLiveData()
    val mListMovieTopRate: LiveData<List<Movie>?> = _mListMovieTopRate

    @SuppressLint("CheckResult")
    fun getListTopRate(apiKey: String){
        Utils.createRetrofit().topRate(apiKey, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _mListMovieTopRate.postValue(movie.results)
            }, { _ ->
                _mListMovieTopRate.postValue(null)
            })
    }
}