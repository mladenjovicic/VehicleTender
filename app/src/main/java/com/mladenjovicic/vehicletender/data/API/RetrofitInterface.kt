package com.mladenjovicic.vehicletender.data.API

import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.CarModelDB
import retrofit2.Call
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {

    @GET("locations")
    fun getLocationList():Call<List<LocationModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("locations")
    fun createLocation(@Body  newLocation: LocationModelAPI):Call<LocationModelAPI>


    @GET("Status")
    fun getStatusList():Call<List<StatusModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("Status")
    fun CreateStatus(@Body newStatus:StatusModelAPI):Call<StatusModelAPI>

    @GET("Manufacturer")
    fun getManufacturerList():Call<List<ManufacturerModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("Manufacturer")
    fun CreateManufacturer(@Body newStatus:ManufacturerModelAPI):Call<ManufacturerModelAPI>

    @GET("bid")
    fun getBidList():Call<List<BidModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("bid")
    fun CreateBid(@Body newBid:BidModelAPI):Call<BidModelAPI>

    @GET("CarModel")
    fun getCarModelList():Call<List<CarModelApi>>

    @Headers("Content-Type: application/json")
    @POST("CarModel")
    fun CreateCarModel(@Body newCarModel: CarModelApi):Call<CarModelApi>

    @GET("tender")
    fun getTenderList():Call<List<TenderModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tender")
    fun CreateTender(@Body newTenderModelAPI: TenderModelAPI):Call<TenderModelAPI>

    @GET("carStock")
    fun getCarStockList():Call<List<StockInfoModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("carStock")
    fun CreateCarStock(@Body newStockInfoModelAPI: StockInfoModelAPI):Call<StockInfoModelAPI>

    @GET("tenderStock")
    fun getTenderStock():Call<List<TenderStockModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tenderStock")
    fun CreateTenderStock(@Body newStockInfoModelAPI: TenderStockModelAPI):Call<TenderStockModelAPI>

    @GET("tenderUser")
    fun getTenderUser():Call<List<TenderUserModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tenderUser")
    fun CreateTenderUser(@Body newStockInfoModelAPI: TenderUserModelAPI):Call<TenderUserModelAPI>


}