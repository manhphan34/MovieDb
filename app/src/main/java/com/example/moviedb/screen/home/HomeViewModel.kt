package com.example.moviedb.screen.home

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository: MovieRepository) : BaseViewModel() {
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadData(page: Int) {
        val disposable = repository.getMovies(page).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                movies.value = it
            }, {
                //do something
            })
        launchDisposable { disposable }
    }
}