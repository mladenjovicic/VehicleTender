package com.mladenjovicic.vehicletender.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VTApiInstance {
    companion object{
        val BASE_URL = "http://192.168.1.5:45458/api/"
        //val  BASE_URL = "https://raw.githubusercontent.com/mladenjovicic/arraRep/main/location/"
        fun getVTAPIInstance(): Retrofit {
            return  Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}