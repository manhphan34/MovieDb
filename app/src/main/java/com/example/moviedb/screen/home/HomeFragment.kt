package com.example.moviedb.screen.home

import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.util.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_home.recycleMovies
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    val homeAdapter = HomeAdapter(::openDetailMovie)

    companion object {
        const val TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT"
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override val viewModel by viewModel<HomeViewModel>()

    override val layoutRes: Int = R.layout.fragment_home

    override fun initComponents(view: ViewDataBinding) {
        initRecycle()
        viewModel.loadData(1)
        viewModel.movies.observe(this, Observer {
            homeAdapter.submitList(it as MutableList<Movie>?)
        })
    }

    private fun initRecycle() {
        recycleMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = homeAdapter
        }
    }

    private fun openDetailMovie(movie: Movie) {
    }
}