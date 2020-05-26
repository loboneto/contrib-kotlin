package com.example.contribmontano

import retrofit2.Response
import retrofit2.http.GET

interface GithubService {
    @GET("users/diego3g/repos")
    suspend fun getRepositories(): Response<List<Repository>>
}
