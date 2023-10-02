package com.example.kotlinpractice4

import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}