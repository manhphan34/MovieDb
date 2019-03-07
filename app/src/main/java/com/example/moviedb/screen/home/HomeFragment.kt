package com.example.moviedb.screen.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.moviedb.R
import com.example.moviedb.R.string
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.screen.detail.DetailFragment
import com.example.moviedb.util.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_home.recycleMovies
import kotlinx.android.synthetic.main.fragment_home.swipeRefreshLayoutHome
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), OnRefreshListener {
    private val homeAdapter = HomeAdapter(::openDetailMovie)
    private val endlessScrollListener = EndlessScrollListener(::onLoadMore)

    companion object {
        const val TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT"
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override val viewModel by viewModel<HomeViewModel>()

    override val layoutRes: Int = R.layout.fragment_home

    override fun initComponents(view: FragmentHomeBinding) {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            if (swipeRefreshLayoutHome.isRefreshing) swipeRefreshLayoutHome.isRefreshing = false
            homeAdapter.submitList(it)
        })
        viewModel.errorLoadMore.observe(viewLifecycleOwner, Observer {
            showMessage(getString(string.error_load_data))
        })
        viewModel.errorRefresh.observe(viewLifecycleOwner, Observer {
            if (swipeRefreshLayoutHome.isRefreshing) swipeRefreshLayoutHome.isRefreshing = false
            showMessage(getString(string.error_load_data))
        })

        swipeRefreshLayoutHome.setOnRefreshListener(this)
        swipeRefreshLayoutHome.setColorScheme(
            android.R.color.holo_green_light,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_bright
        )
        loadMore()
        initRecycle()
        viewModel.loadDataFirstTime()
    }

    override fun onRefresh() {
        viewModel.movies.value?.clear()
        viewModel.currentPage = 1
        viewModel.reFreshData()
    }

    private fun initRecycle() {
        recycleMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = homeAdapter
        }
    }

    private fun openDetailMovie(movie: Movie) {
        val detail = DetailFragment.newInstance(movie)
        replaceFragment(
            R.id.frame_container,
            detail, true,
            DetailFragment.TAG
        )
    }

    private fun loadMore() {
        recycleMovies.addOnScrollListener(endlessScrollListener)
    }

    private fun onLoadMore() {
        if (viewModel.isLoading.value == false) {
            viewModel.loadMoreData(viewModel.currentPage)
        }
    }
}