package com.example.moviedb.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.util.di.modules
import org.koin.android.ext.android.startKoin

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: ViewModel

    abstract fun initComponent(saveInstantState: Bundle?)

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        startKoin(this, modules)
        initComponent(savedInstanceState)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}