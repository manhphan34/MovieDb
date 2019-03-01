package com.example.moviedb.util.di

import com.example.moviedb.BuildConfig
import com.example.moviedb.data.remote.api.ApiClient
import com.example.moviedb.util.Constant
import com.example.moviedb.util.di.Constants.CONNECTION_TIMEOUT
import com.example.moviedb.util.di.Constants.READ_TIMEOUT
import com.example.moviedb.util.di.Constants.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofit() }
    single { provideApi(get()) }
}

object Constants {
    const val READ_TIMEOUT: Long = 30
    const val WRITE_TIMEOUT: Long = 30
    const val CONNECTION_TIMEOUT: Long = 30
}

fun provideRetrofit(): Retrofit {
    val okHttpClient = provideOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.addInterceptor {
        var request = it.request()
        val url =
            request.url().newBuilder().addQueryParameter(Constant.API_KEY_PAR, BuildConfig.API_KEY)
                .build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(logging)
    httpClientBuilder.readTimeout(
        READ_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.writeTimeout(
        WRITE_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.connectTimeout(
        CONNECTION_TIMEOUT, TimeUnit.SECONDS
    )
    return httpClientBuilder.build()
}

fun provideApi(retrofit: Retrofit): ApiClient {
    return retrofit.create(ApiClient::class.java)
}
