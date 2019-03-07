package com.example.moviedb.screen.favorite

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(val movieRepo: MovieRepository) : BaseViewModel() {
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    fun loadData() {
        val disposable = movieRepo.getAllMovie().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                movies.value = it
            })
        launchDisposable { disposable }
    }
}