package com.example.nguyenduchuy.fragment.detailmovie.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.DetailMovieFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.detailmovie.viewmodel.DetailMovieViewModel
import com.example.nguyenduchuy.fragment.detailmovie.adapter.MovieDetailListAdapter
import com.example.nguyenduchuy.util.Utils


class DetailMovieFragment : BaseFragment<DetailMovieViewModel, DetailMovieFragmentBinding>() {

    private var linkTrailer: String? = null
    lateinit var mAdapterSimilarDetail: MovieDetailListAdapter
    lateinit var mAdapterRecommendationsDetail: MovieDetailListAdapter

    override fun layoutId(): Int = R.layout.detail_movie_fragment

    override fun viewModelClass(): Class<DetailMovieViewModel> = DetailMovieViewModel::class.java

    override fun initCallApi() {
        val movieId = arguments!!.getInt(MOVIE_ID)
        movieId.let {
            viewModel.getDetail(it, getString(R.string.api_key))
        }
    }

    override fun initLiveData() {
        viewModel.detail.observe(this, {
            Glide.with(this.context!!).load(this.context!!.getString(R.string.url_image) + it.poster_path).into(binding.imvPoster)
            binding.tvTitle.text = it.title
            binding.tvOverview.text = it.overview
            binding.tvVoteAverage.text = """Vote: ${it.vote_average}"""
        })
        viewModel.video.observe(this, {
            if(it.isNotEmpty()){
                linkTrailer = "https://www.youtube.com/watch?v=" + it[0].key
            }
        })
        viewModel.similarVideo.observe(this, {
            mAdapterSimilarDetail.submitList(it)
        })
        viewModel.recommendations.observe(this, {
            mAdapterRecommendationsDetail.submitList(it)
        })
    }

    override fun initView() {
        binding.tvTrailer.setOnClickListener {
            linkTrailer?.let {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(linkTrailer)
                intent.setPackage("com.google.android.youtube")
                startActivity(intent)
            }
        }

        mAdapterRecommendationsDetail = MovieDetailListAdapter {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, it.id)
            Utils.addFragment(activity!!.supportFragmentManager, R.id.fl_Main_Container,
                newInstance(bundle))
        }

        mAdapterSimilarDetail = MovieDetailListAdapter {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, it.id)
            Utils.addFragment(activity!!.supportFragmentManager, R.id.fl_Main_Container,
                newInstance(bundle))
        }

        binding.rcvRecommendations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        binding.rcvRecommendations.adapter = mAdapterRecommendationsDetail

        binding.rcvSimilar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        binding.rcvSimilar.adapter = mAdapterSimilarDetail
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
        fun newInstance(bundle: Bundle) = DetailMovieFragment().apply {
            arguments = bundle
        }
    }
}