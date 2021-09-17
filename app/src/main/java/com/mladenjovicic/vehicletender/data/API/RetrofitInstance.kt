package com.mladenjovicic.vehicletender.data.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val client = OkHttpClient.Builder().build()
        //val BASE_URL = "http://192.168.0.38:45455/api/"
        //val  BASE_URL = "https://raw.githubusercontent.com/mladenjovicic/arraRep/main/location/"
        val BASE_URL ="http://10.0.2.2:3004/"
        fun getRetrofit(): Retrofit {
            return  Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}