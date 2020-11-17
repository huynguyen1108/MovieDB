package com.example.nguyenduchuy.fragment.search.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : ViewModel() {
    val subject: BehaviorSubject<String> = BehaviorSubject.create()
    val searchResponse: MutableLiveData<List<Movie>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun callApiSearch(apiKey: String){
        subject.debounce(300, TimeUnit.MILLISECONDS)
            .map(CharSequence::toString)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .switchMap{query -> Utils.createRetrofit().search(apiKey, query)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchResponse.postValue(it.results)
            }
    }
}