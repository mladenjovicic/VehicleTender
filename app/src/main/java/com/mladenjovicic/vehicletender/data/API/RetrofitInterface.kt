package com.mladenjovicic.vehicletender.data.API

import com.mladenjovicic.vehicletender.data.model.api.DefaultResponse
import retrofit2.Call
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.LocationModelTest
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {

    @GET("locations")
    fun getLocationList():Call<List<LocationModelAPI>>

    //@Headers("Content-Type: application/json")
    @POST("locations")
    fun createLocation(@Body  newLocation: LocationModelAPI):Call<LocationModelAPI>

    @POST("locations")
    fun createNewLocation(@Field("id") id:Int,
                          @Field("City") City:String,
                          @Field("ZipCode") ZipCode:String):Call<DefaultResponse>

    @GET("Status")
    fun getStatusList():Call<List<StatusModelAPI>>

    @GET("Manufacturer")
    fun getManufacturerList()
}