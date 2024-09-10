package com.example.currencyconversionapp

import com.example.currencyconversionapp.model.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("/v2/currency")
    fun getExchangeRate(
        @Query("api_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): Call<CurrencyResponse>
}
