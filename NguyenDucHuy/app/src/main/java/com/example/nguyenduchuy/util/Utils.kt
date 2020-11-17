package com.example.nguyenduchuy.util

import android.annotation.SuppressLint
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.nguyenduchuy.BuildConfig
import com.example.nguyenduchuy.api.ApiService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Utils {
    @SuppressLint("RestrictedApi")
    fun addFragment(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment,
        isBackStack: Boolean
    ){
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        fragmentManager?.executePendingTransactions();
        val frag = fragmentManager?.findFragmentByTag(fragment.javaClass.simpleName)
        if(frag == null){
            transaction?.add(frameId, fragment, fragment.javaClass.simpleName)
        } else {
            for (fragment in fragmentManager.fragments) {
                if (fragment.isVisible)
                    transaction?.hide(fragment)
            }
            transaction?.show(fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)!!)
        }
        if(isBackStack){
            transaction?.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction?.commit()
    }

    @SuppressLint("RestrictedApi")
    fun addFragment(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ){
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        fragmentManager?.executePendingTransactions();

        for (fragment in fragmentManager!!.fragments) {
            if (fragment.isVisible)
                transaction?.hide(fragment)
        }
        transaction?.add(frameId, fragment, fragment.javaClass.simpleName)

        transaction?.addToBackStack(fragment.javaClass.simpleName)

        transaction?.commit()
    }

    fun createRetrofit(): ApiService = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(provideOkHttpClient())
            .addConverterFactory(provideGsonConverterFactory())
            .addCallAdapterFactory(provideRxAdapter())
            .build()
        .create(ApiService::class.java)


    private fun provideOkHttpClient() = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .build()



    private fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }

    private fun provideGson(): Gson = Gson()

    private fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(provideGson())

    private fun provideRxAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
