package com.example.nguyenduchuy.fragment.toprated.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.TopRatedFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.toprated.viewmodel.TopRatedViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie

class TopRatedFragment : BaseFragment<TopRatedViewModel, TopRatedFragmentBinding>() {

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    override fun layoutId(): Int = R.layout.top_rated_fragment

    override fun viewModelClass(): Class<TopRatedViewModel> = TopRatedViewModel::class.java

    override fun initCallApi() {
        viewModel.getListTopRate(getString(R.string.api_key))
    }

    override fun initLiveData() {
        viewModel.mListMovieTopRate.observe(this, {
            mAdapter.submitList(it)
        })
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rcvMovie.layoutManager = GridLayoutManager(context, 3)
        binding.rcvMovie.adapter = mAdapter
    }

}