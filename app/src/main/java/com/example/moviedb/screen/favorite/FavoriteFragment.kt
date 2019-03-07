package com.example.moviedb.screen.favorite

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentFavoriteBinding
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.screen.detail.DetailFragment
import com.example.moviedb.screen.home.HomeAdapter
import com.example.moviedb.util.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_favorite.recycleMovies
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    val homeAdapter = HomeAdapter(::openDetailMovie)
    override val viewModel by viewModel<FavoriteViewModel>()
    override val layoutRes: Int = R.layout.fragment_favorite

    companion object {
        const val TAG = "favorite"
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }

    override fun initComponents(view: FragmentFavoriteBinding) {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            val list = it as MutableList<Movie>
            homeAdapter.submitList(list)
        })
        viewModel.loadData()
        initRecycle()
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
}