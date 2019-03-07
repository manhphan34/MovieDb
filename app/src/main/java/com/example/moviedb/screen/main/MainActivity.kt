package com.example.moviedb.screen.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.base.BaseActivity
import com.example.moviedb.data.model.Movie
import com.example.moviedb.screen.favorite.FavoriteFragment
import com.example.moviedb.screen.home.HomeFragment
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.util.extension.addFragmentToActivity
import com.example.moviedb.util.extension.clearAllFragment
import com.example.moviedb.util.extension.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.bottomNavigationMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()

    companion object {
        var TAG_CURRENT: String? = null
        const val TAG_SAVE_INSTANCE = "TAG_SAVE_INSTANCE"
    }

    val homeFragment =
        supportFragmentManager.findFragmentByTag(HomeFragment.TAG_HOME_FRAGMENT) ?: HomeFragment.newInstance()

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            val tag = savedInstanceState.getString(TAG_SAVE_INSTANCE)
            val fragment =
                supportFragmentManager.findFragmentByTag(tag)
            if (fragment != null) {
                replaceFragmentInActivity(R.id.frame_container, fragment, true, tag)
            }
        }
    }

    override fun initComponent(saveInstantState: Bundle?) {
        if (saveInstantState == null) {
            addFragmentToActivity(
                R.id.frame_container,
                homeFragment, false,
                HomeFragment.TAG_HOME_FRAGMENT
            )
            TAG_CURRENT = HomeFragment.TAG_HOME_FRAGMENT
        }
        setNavigationListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TAG_SAVE_INSTANCE, TAG_CURRENT)
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
        TAG_CURRENT = HomeFragment.TAG_HOME_FRAGMENT
        replaceFragmentInActivity(
            R.id.frame_container, homeFragment,
            true, HomeFragment.TAG_HOME_FRAGMENT
        )
    }

    private fun replaceFragmentFavorite() {
        TAG_CURRENT = FavoriteFragment.TAG
        val favoriteFragment = FavoriteFragment.newInstance()
        replaceFragmentInActivity(
            R.id.frame_container, favoriteFragment,
            true, FavoriteFragment.TAG
        )
    }
}
