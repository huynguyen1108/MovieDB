package com.example.nguyenduchuy.fragment.popular.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.PopularFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.popular.viewmodel.PopularViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie

class PopularFragment : BaseFragment<PopularViewModel, PopularFragmentBinding>() {

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun layoutId(): Int = R.layout.popular_fragment

    override fun viewModelClass(): Class<PopularViewModel> = PopularViewModel::class.java

    override fun initCallApi() {
        viewModel.getListPopular(getString(R.string.api_key))
    }

    override fun initLiveData() {
        viewModel.mListMoviePopular.observe(this, {
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