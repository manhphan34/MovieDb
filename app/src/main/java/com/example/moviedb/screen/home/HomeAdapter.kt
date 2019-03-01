package com.example.moviedb.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.base.BaseAdapter
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemMovieBinding

class HomeAdapter(private val itemClickListener: ((Movie) -> Unit)? = null) :
    BaseAdapter<Movie>(callBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }) {

    override fun bind(binding: ViewDataBinding, item: Movie) {
        if (binding is ItemMovieBinding) binding.movie = item
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context), R.layout.item_movie,
            parent, false
        ).apply {
            root.setOnClickListener {
                movie?.apply {
                    itemClickListener?.invoke(this)
                }
            }
        }
    }
}