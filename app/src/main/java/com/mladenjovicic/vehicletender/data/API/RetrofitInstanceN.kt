package com.mladenjovicic.vehicletender.data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceN {
//val BASE_URL = "https://192.168.0.38:45456/api/"

private val BASE_URL = "http://10.0.2.2:3004/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
}}