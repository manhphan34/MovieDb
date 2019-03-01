package com.example.moviedb.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : LifecycleObserver, ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun launchDisposable(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}