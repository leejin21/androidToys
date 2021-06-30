package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("top-headlines")
    fun requestAllData(@Query("apiKey") apiKey: String,
                       @Query("country") country: String,
                       @Query("category") category: String): Call<ArticleModel>
}