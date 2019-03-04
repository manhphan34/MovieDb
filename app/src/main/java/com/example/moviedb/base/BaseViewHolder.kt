package com.example.moviedb.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)