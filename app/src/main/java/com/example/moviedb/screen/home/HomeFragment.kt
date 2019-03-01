package com.example.moviedb.screen.home

import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override val viewModel by viewModel<HomeViewModel>()

    override val layoutRes: Int = R.layout.fragment_home

    override fun initComponents(view: ViewDataBinding) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}