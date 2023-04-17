package com.example.jamsostekapps.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jamsostekapps.data.remote.Article
import com.example.jamsostekapps.data.remote.NewsResp
import com.example.jamsostekapps.databinding.FragmentNewsBinding
import com.example.jamsostekapps.domain.util.Resource
import com.example.jamsostekapps.ui.newsdetail.NewsDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsPaperFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsPaperViewModel by viewModels()
    private lateinit var data: NewsResp
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCanLoadMore(true)
        setupAdapter()
        onLoadObserver()
    }

    private fun onLoadObserver() {
        viewModel.getListNews().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    toogleLoading()
                }
                is Resource.Success -> {
                    toogleLoading()
                    response.data?.let {
                        data = it
                        response.data.articles.random().let { article ->
                            Glide.with(requireContext()).load(article.urlToImage).into(binding.imgHeader)
                            binding.tvDate.text = article.publishedAt
                        }
                        newsAdapter.setListData((response.data.articles))
                    }
                }
                is Resource.Error -> {
                    toogleLoading()
                }
            }
        }
    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter()
        val layoutManager = GridLayoutManager(
            activity,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        with(binding.rvOtherNews) {
            adapter = newsAdapter
            this.layoutManager = layoutManager
        }

        binding.nsv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, dx, dy, oldScrollX, oldScrollY ->
            if(v.getChildAt(v.childCount - 1) != null) {
                if ((dy >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                    dy > oldScrollY
                ) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems =
                        layoutManager.findLastCompletelyVisibleItemPosition()
                    if (viewModel.canLoadMore.value == true) {
                        if (viewModel.loadMore.value == false) {
                            if (visibleItemCount + pastVisibleItems > totalItemCount) {
                                loadMoreNews()
                            }
                        }
                    }
                }
            }
        })

        newsAdapter.onItemClicked = {
            val intent = Intent(requireContext(), NewsDetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
    }

    private fun loadMoreNews() {
        viewModel.loadMoreListNews().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    viewModel.setLoadMore(true)
                    toogleLoading()
                }
                is Resource.Success -> {
                    response.data?.let {
                        viewModel.setLoadMore(false)
                        newsAdapter.loadMoreData(it.articles)
                        if (it.articles == emptyList<Article>()) {
                            viewModel.setCanLoadMore(false)
                        }
                        toogleLoading()
                    }
                }
                is Resource.Error -> {
                    viewModel.setLoadMore(false)
                    viewModel.setCanLoadMore(false)
//                    Toast.makeText(requireContext(),"Sorry we have truble now, try again latter..",Toast.LENGTH_LONG).show()
                    toogleLoading()
                }
            }
        }
    }

    private fun toogleLoading() {
        if (viewModel.page.value == 1) {
            if (binding.defaultProgress.isShown) {
                binding.defaultProgress.visibility = View.GONE
            } else {
                binding.defaultProgress.visibility = View.VISIBLE
            }
        } else {
            if (binding.loadMoreProgress.isShown) {
                binding.loadMoreProgress.visibility = View.GONE
            } else {
                binding.loadMoreProgress.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}