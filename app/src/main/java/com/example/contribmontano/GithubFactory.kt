package com.example.contribmontano

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object GithubFactory {

    private const val BASE_URL = "https://api.github.com/"

    //create HTTP interceptor to logs the request
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS) //adiciona timeout
        .connectTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .addInterceptor(interceptor) //added interceptor to client
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    fun githubService(): GithubService = retrofit.create(GithubService::class.java)
}
