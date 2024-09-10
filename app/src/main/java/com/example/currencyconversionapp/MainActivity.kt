package com.example.currencyconversionapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconversionapp.model.CurrencyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.currencyconversionapp.CurrencyApiService
import com.example.currencyconversionapp.RetrofitClient.RetrofitClient



class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private val apiKey = "92fbd9119a7292929b0c08e94d6c7832f8ef3e19"  // actual API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)

        val apiService = RetrofitClient.getClient().create(CurrencyApiService::class.java)
        val call = apiService.getExchangeRate(apiKey, "USD", "ZAR", 1.0)

        call.enqueue(object : Callback<CurrencyResponse> {
            override fun onResponse(call: Call<CurrencyResponse>, response: Response<CurrencyResponse>) {
                if (response.isSuccessful) {
                    val currencyResponse = response.body()
                    textViewResult.text = currencyResponse?.rates?.get("ZAR")?.rateForAmount

                } else {
                    textViewResult.text = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                textViewResult.text = "Error: ${t.message}"
            }
        })
    }
}
