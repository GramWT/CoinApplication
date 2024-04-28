package com.example.coinapplication.retrofit

import com.example.coinapplication.data.CoinApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : CoinApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }
}