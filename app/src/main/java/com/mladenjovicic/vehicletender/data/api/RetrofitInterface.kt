package com.mladenjovicic.vehicletender.data.api

import retrofit2.Call
import retrofit2.http.GET
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI

interface RetrofitInterface {

    @GET("Location")
    fun getLocationList():Call<List<LocationModelAPI>>

    @GET("Status")
    fun getStatusList():Call<List<StatusModelAPI>>

    @GET("ManufacturerContoroller")
    fun getManufacturerList()
}