package com.mladenjovicic.vehicletender.API

import androidx.databinding.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.mladenjovicic.vehicletender.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.model.api.LocationModelTest
import retrofit2.Response

interface VTAPIServiceInterface {

    @GET("location1.json")
    suspend fun getLocationList():Call<List<LocationModelAPI>>
}