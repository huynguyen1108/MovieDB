package com.example.nguyenduchuy.fragment.upcoming.view

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.UpcomingFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.upcoming.viewmodel.UpComingViewModel

class UpcomingFragment : BaseFragment<UpComingViewModel, UpcomingFragmentBinding>() {

    override fun initLiveData(){
        viewModel.mListMovieUpcoming.observe(viewLifecycleOwner, {
            it?.let {
                mAdapter.submitList(it)
            } ?: kotlin.run {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun layoutId(): Int = R.layout.upcoming_fragment

    override fun viewModelClass(): Class<UpComingViewModel> = UpComingViewModel::class.java

    override fun initCallApi() {
        viewModel.getListUpcoming(getString(R.string.api_key))
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rcvMovie.layoutManager = GridLayoutManager(context, 3)
        binding.rcvMovie.adapter = mAdapter
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }


}