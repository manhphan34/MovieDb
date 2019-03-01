package com.example.moviedb.screen.main

import android.os.Bundle
import com.example.moviedb.R
import com.example.moviedb.R.id
import com.example.moviedb.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.bottomNavigationMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()

    override fun initComponent(saveInstantState: Bundle?) {
        setNavigationListener()
    }

    override fun getLayout(): Int = R.layout.activity_main

    private fun setNavigationListener() {
        bottomNavigationMain.setOnNavigationItemSelectedListener  {
            when (it.itemId) {
                id.mnu_home -> replaceFragmentHome()
                id.mnu_favorite -> replaceFragmentFavorite()
            }
            true
        }
    }

    private fun replaceFragmentHome() {

    }

    private fun replaceFragmentFavorite() {

    }
}
