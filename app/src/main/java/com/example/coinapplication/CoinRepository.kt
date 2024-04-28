package com.example.coinapplication

import com.example.coinapplication.data.CoinApi
import com.example.coinapplication.model.CoinsItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinRepository {

    private val coinService: CoinApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    suspend fun getCoins(page: Int): List<CoinsItem?>? {
        return coinService.getCoinList(page, 20).data?.coins
    }
}