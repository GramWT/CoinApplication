package com.example.coinapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapplication.CoinRepository
import com.example.coinapplication.retrofit.RetrofitInstance
import com.example.coinapplication.model.CoinListResponse
import com.example.coinapplication.model.CoinsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CoinViewModel:ViewModel() {

    private var coinListLiveData = MutableLiveData<List<CoinsItem?>>()
    val coins: LiveData<List<CoinsItem?>>
        get() = coinListLiveData

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private var lastUpdateTime = 0L

    var currentPage = 1

    init {
        fetchCoins(currentPage)
//        startAutoUpdate()
    }


    private fun startAutoUpdate() {
        viewModelScope.launch {
            while (true) {
                delay(10_000) // Wait for 10 seconds
                fetchCoins()
            }
        }
    }

    fun fetchNextPage() {
        currentPage++
        fetchCoins(currentPage)
    }

    private fun fetchCoins(page: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedCoins = CoinRepository().getCoins(page)
                coinListLiveData.postValue(fetchedCoins!!)
            }
        }
    }

    private fun fetchCoins() {
        viewModelScope.launch {
            try {
                val fetchedCoins = CoinRepository().getCoins(currentPage)
                coinListLiveData.postValue(fetchedCoins!!)
                _error.value = false // Reset error state if fetch is successful
                lastUpdateTime = System.currentTimeMillis() // Update last update time
            } catch (e: HttpException) {
                // Handle HTTP error
                if (e.code() == 429) {
                    // HTTP 429 error (Too Many Requests)
                    _error.value = true
                }
            } catch (e: Exception) {
                // Handle other exceptions
                _error.value = true
            }
        }
    }

    fun observeCoinLiveData():LiveData<List<CoinsItem?>>{
        return coinListLiveData
    }
}