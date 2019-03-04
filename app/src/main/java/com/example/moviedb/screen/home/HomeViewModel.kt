package com.example.moviedb.screen.home

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository: MovieRepository) : BaseViewModel() {
    val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val errorLoadMore: MutableLiveData<String> = MutableLiveData()
    val errorRefresh: MutableLiveData<String> = MutableLiveData()
    var currentPage = 1
    val isFirstTimeLoadData: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun loadMoreData(page: Int) {
        isLoading.value = true
        val disposable = repository.getMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                updateData(it.toMutableList())
                isLoading.value = false
                currentPage++
            }, {
                isLoading.value = false
                errorLoadMore.value = it.message
            })
        launchDisposable { disposable }
    }

    fun reFreshData() {
        val disposable = repository.getMovies(currentPage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                updateData(it.toMutableList())
                currentPage++
            }, {
                errorRefresh.value = it.message
            })
        launchDisposable { disposable }
    }

    fun loadDataFirstTime() {
        isFirstTimeLoadData.value = true
        val disposable = repository.getMovies(currentPage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                updateData(it.toMutableList())
                isFirstTimeLoadData.value = false
                currentPage++
            }, {
                isFirstTimeLoadData.value = false
                errorRefresh.value = it.message
            })
        launchDisposable { disposable }
    }

    private fun updateData(movies: MutableList<Movie>) {
        if (this.movies.value == null) {
            this.movies.value = movies
        } else {
            val moviesCp: MutableList<Movie> = this.movies.value!!
            moviesCp.addAll(movies)
            this.movies.value = moviesCp
        }
    }
}