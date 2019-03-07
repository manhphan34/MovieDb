package com.example.moviedb.screen.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentDetailBinding
import com.example.moviedb.util.extension.replaceFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(), View.OnClickListener {
    override val viewModel by viewModel<DetailViewModel>()
    override val layoutRes: Int = R.layout.fragment_detail

    companion object {

        private const val ARGUMENTS_MOVIE = "MOVIE"
        const val TAG = "DetailFragment"

        fun newInstance(movie: Movie) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENTS_MOVIE, movie)
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val fragment = fragmentManager?.findFragmentByTag(TAG)
            if (fragment != null) {
                replaceFragment(R.id.frame_container, fragment, true, TAG)
            }
        }
    }

    override fun initComponents(view: FragmentDetailBinding) {
        view.imageButtonFavorite.setOnClickListener(this)
        viewModel.loadData(arguments?.getParcelable(ARGUMENTS_MOVIE) as Movie)
        viewModel.favorite.observe(this, Observer {
            when (it) {
                0 -> view.imageButtonFavorite.setImageResource(R.drawable.ic_none_favorite)
                1 -> view.imageButtonFavorite.setImageResource(R.drawable.ic_favorite)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TAG, TAG)
    }

    override fun onClick(v: View?) {
        if (viewModel.favorite.value == 0) viewModel.insertMovie()
        else viewModel.deleteMovie()
    }
}