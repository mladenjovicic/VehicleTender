package com.mladenjovicic.vehicletender.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

        val BASE_URL = "http://192.168.0.38:45455/api/"
        fun getRetrofit(): Retrofit {
            return  Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create()).build()
        }
}