package com.example.coinapplication.data

import com.example.coinapplication.model.CoinListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("/v2/coins")
    suspend fun getCoinList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CoinListResponse
}