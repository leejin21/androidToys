package com.example.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GroupRetrofitServiceImpl {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
            // json 형식 -> Data class 형식으로 자동 변환
        .build()

    val service_ct_tab: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)
}