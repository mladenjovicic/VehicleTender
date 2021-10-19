package com.mladenjovicic.vehicletender.data.API

import androidx.databinding.Bindable
import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.CarModelDB
import retrofit2.Call
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {

    @GET("api/location")
    fun getLocationList(@Header ("Authorization") Token:String):Call<List<LocationModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/location/addlocation")
    fun createLocation(@Header ("Authorization") Token:String, @Body  newLocation: LocationModelAPI):Call<LocationModelAPI>


    @GET("Status")
    fun getStatusList(@Header ("Authorization") Token:String):Call<List<StatusModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("Status")
    fun CreateStatus(@Header ("Authorization") Token:String, @Body newStatus:StatusModelAPI):Call<StatusModelAPI>

    @GET("api/manufacturers")
    fun getManufacturerList(@Header ("Authorization") Token:String):Call<List<ManufacturerModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("Manufacturer")
    fun CreateManufacturer(@Header ("Authorization") Token:String, @Body newStatus:ManufacturerModelAPI):Call<ManufacturerModelAPI>

    @GET("bid")
    fun getBidList(@Header ("Authorization") Token:String):Call<List<BidModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("bid")
    fun CreateBid(@Header ("Authorization") Token:String, @Body newBid:BidModelAPI):Call<BidModelAPI>

    @GET("CarModel")
    fun getCarModelList(@Header ("Authorization") Token:String):Call<List<CarModelApi>>

    @Headers("Content-Type: application/json")
    @POST("CarModel")
    fun CreateCarModel(@Header ("Authorization") Token:String, @Body newCarModel: CarModelApi):Call<CarModelApi>

    @GET("tender")
    fun getTenderList(@Header ("Authorization") Token:String):Call<List<TenderModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tender")
    fun CreateTender(@Header ("Authorization") Token:String, @Body newTenderModelAPI: TenderModelAPI):Call<TenderModelAPI>

    @GET("carStock")
    fun getCarStockList(@Header ("Authorization") Token:String):Call<List<StockInfoModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("carStock")
    fun CreateCarStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: StockInfoModelAPI):Call<StockInfoModelAPI>

    @GET("tenderStock")
    fun getTenderStock(@Header ("Authorization") Token:String):Call<List<TenderStockModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tenderStock")
    fun CreateTenderStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: TenderStockModelAPI):Call<TenderStockModelAPI>

    @GET("tenderUser")
    fun getTenderUser(@Header ("Authorization") Token:String):Call<List<TenderUserModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("tenderUser")
    fun CreateTenderUser(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: TenderUserModelAPI):Call<TenderUserModelAPI>

   /* @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("token")
    fun getToken(@Body newGetToken:RequestToken):Call<GetTokenAPI>*/
/*
    @Headers("Content-Type: application/x-www-form-urlencoded")*/
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("token")
    fun getToken(@Field("grant_type") grant_type:String, @Field("username") username:String, @Field("password") password:String ):Call<GetTokenAPI>

    //@FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("api/users")
    fun getListUser(@Header ("Authorization") Token:String):Call<List<UserProfilAPI>>

   @GET("api/user?")
    fun getUserProfil(@Header ("Authorization") Token:String, @Query("email") useremail:String):Call<UserProfilAPI>
}