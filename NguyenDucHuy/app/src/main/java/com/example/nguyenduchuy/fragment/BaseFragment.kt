package com.example.nguyenduchuy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.fragment.detailmovie.adapter.MovieDetailListAdapter
import com.example.nguyenduchuy.fragment.detailmovie.view.DetailMovieFragment
import com.example.nguyenduchuy.fragment.upcoming.adapter.MovieListAdapter
import com.example.nguyenduchuy.fragment.upcoming.model.Movie
import com.example.nguyenduchuy.util.Utils

abstract class BaseFragment<VM: ViewModel, T: ViewDataBinding> : Fragment(){
    lateinit var viewModel: VM
    lateinit var binding: T
    lateinit var mAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)).get(
            viewModelClass())
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initLiveData()
        initCallApi()
        initView()
    }

    protected abstract fun layoutId() : Int

    protected abstract fun viewModelClass() : Class<VM>

    protected open fun onClick(item: Movie){
        val bundle : Bundle = Bundle()
        bundle.putInt(DetailMovieFragment.MOVIE_ID, item.id)
        Utils.addFragment(activity!!.supportFragmentManager, R.id.fl_Main_Container,
            DetailMovieFragment.newInstance(bundle), true)
    }

    protected abstract fun initCallApi()

    protected abstract fun initLiveData()

    protected abstract fun initView()

    private fun initRecyclerView(){
        mAdapter = MovieListAdapter{ item -> onClick(item)}
    }


}