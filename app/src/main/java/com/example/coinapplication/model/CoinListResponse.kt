package com.example.coinapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinListResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class Stats(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("totalExchanges")
    val totalExchanges: Int? = null,

    @field:SerializedName("totalMarkets")
    val totalMarkets: Int? = null,

    @field:SerializedName("totalMarketCap")
    val totalMarketCap: String? = null,

    @field:SerializedName("total24hVolume")
    val total24hVolume: String? = null,

    @field:SerializedName("totalCoins")
    val totalCoins: Int? = null
) : Parcelable

@Parcelize
data class Data(

    @field:SerializedName("stats")
    val stats: Stats? = null,

    @field:SerializedName("coins")
    val coins: List<CoinsItem?>? = null
) : Parcelable

@Parcelize
data class CoinsItem(

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("marketCap")
    val marketCap: String? = null,

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("change")
    val change: String? = null,

    @field:SerializedName("btcPrice")
    val btcPrice: String? = null,

    @field:SerializedName("listedAt")
    val listedAt: Int? = null,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("sparkline")
    val sparkline: List<String?>? = null,

    @field:SerializedName("24hVolume")
    val jsonMember24hVolume: String? = null,

    @field:SerializedName("tier")
    val tier: Int? = null,

    @field:SerializedName("coinrankingUrl")
    val coinrankingUrl: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("lowVolume")
    val lowVolume: Boolean? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("iconUrl")
    val iconUrl: String? = null
) : Parcelable
