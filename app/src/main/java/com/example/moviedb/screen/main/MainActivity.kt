package com.example.moviedb.screen.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.base.BaseActivity
import com.example.moviedb.data.model.Movie
import com.example.moviedb.screen.home.HomeFragment
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.util.extension.addFragmentToActivity
import com.example.moviedb.util.extension.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.bottomNavigationMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()
    val homeFragment =
        supportFragmentManager.findFragmentByTag(HomeFragment.TAG_HOME_FRAGMENT) ?: HomeFragment.newInstance()

    override fun initComponent(saveInstantState: Bundle?) {
        addFragmentToActivity(
            R.id.frame_container,
            homeFragment, false,
            HomeFragment.TAG_HOME_FRAGMENT
        )
        setNavigationListener()
    }

    override fun getLayout(): Int = R.layout.activity_main

    private fun setNavigationListener() {
        bottomNavigationMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnu_home -> replaceFragmentHome()
                R.id.mnu_favorite -> replaceFragmentFavorite()
            }
            true
        }
    }

    private fun replaceFragmentHome() {
        replaceFragmentInActivity(
            R.id.frame_container, homeFragment,
            true, HomeFragment.TAG_HOME_FRAGMENT
        )
    }

    private fun replaceFragmentFavorite() {
    }
}
