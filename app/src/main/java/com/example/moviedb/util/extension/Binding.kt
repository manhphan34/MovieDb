package com.example.moviedb.util.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedb.R
import com.example.moviedb.util.Constant

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, imageLink: String?) {
    var path: String = Constant.BASE_URL_IMAGE
    path += imageLink
    Glide.with(imageView.context)
        .load(path)
        .apply(RequestOptions().error(R.drawable.ic_error))
        .into(imageView)
}