package com.example.nguyenduchuy.fragment.search.view

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.SearchFragmentBinding
import com.example.nguyenduchuy.fragment.BaseFragment
import com.example.nguyenduchuy.fragment.search.SearchListAdapter
import com.example.nguyenduchuy.fragment.search.viewmodel.SearchViewModel

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>() {

    private lateinit var searchListAdapter: SearchListAdapter

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun layoutId(): Int = R.layout.search_fragment

    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun initCallApi() {
        viewModel.callApiSearch(getString(R.string.api_key))
    }

    override fun initLiveData() {
        viewModel.searchResponse.observe(this, {
            searchListAdapter.submitList(it)
        })
    }

    override fun initView() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.subject.onNext(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        searchListAdapter = SearchListAdapter()
        binding.rcvSearch.layoutManager = LinearLayoutManager(context)
        binding.rcvSearch.adapter = searchListAdapter
    }

}