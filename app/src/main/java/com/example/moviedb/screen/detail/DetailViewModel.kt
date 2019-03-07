package com.example.moviedb.screen.detail

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.base.BaseViewModel
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(val movieRepo: MovieRepository) : BaseViewModel() {
    var movie: Movie = Movie()
    val id: MutableLiveData<Int> = MutableLiveData()
    val pathImage: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val favorite: MutableLiveData<Int> = MutableLiveData()
    val rate: MutableLiveData<Float> = MutableLiveData()

    fun getFavorite() {
        launchDisposable {
            movieRepo.getMovieFavoriteById(id.value!!).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it != null) favorite.value = 1
                    else favorite.value = 0
                }, {
                    favorite.value = 0
                })
        }
    }

    fun loadData(movie: Movie) {
        this.movie = movie
        id.value = movie.id
        pathImage.value = movie.posterPath
        title.value = movie.title
        rate.value = movie.voteAverage.toFloat()
        getFavorite()
    }

    fun insertMovie() {
        val disposable = Single.create<Boolean> {
            movieRepo.insertMovie(movie)
            it.onSuccess(true)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it) favorite.value = 1
                else favorite.value = 0
            }, {
                favorite.value = 0
            })
        launchDisposable { disposable }
    }

    fun deleteMovie() {
        val disposable = Single.create<Boolean> {
            movieRepo.deleteMovie(movie)
            it.onSuccess(true)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it) favorite.value = 0
                else favorite.value = 1
            }, {
                favorite.value = 1
            })
        launchDisposable { disposable }
    }
}