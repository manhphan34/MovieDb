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
        repository.getMovies(page).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : SingleObserver<List<Movie>> {
                    override fun onSuccess(t: List<Movie>) {
                        movies.value = t
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }
}