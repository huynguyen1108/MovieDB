package com.example.nguyenduchuy.fragment.nowplaying.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.NowPlayingFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.nowplaying.viewmodel.NowPlayingViewModel
import com.example.nguyenduchuy.fragment.upcoming.model.Movie

class NowPlayingFragment : BaseFragment<NowPlayingViewModel, NowPlayingFragmentBinding>() {

    companion object {
        fun newInstance() = NowPlayingFragment()
    }

    override fun layoutId(): Int = R.layout.now_playing_fragment

    override fun viewModelClass(): Class<NowPlayingViewModel> = NowPlayingViewModel::class.java

    override fun initCallApi() {
        viewModel.getListNowPlaying(getString(R.string.api_key))
    }

    override fun initLiveData() {
        viewModel.mListMovieNowPlaying.observe(this, {
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