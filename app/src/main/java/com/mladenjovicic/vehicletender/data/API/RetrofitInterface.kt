package com.mladenjovicic.vehicletender.data.API

import com.mladenjovicic.vehicletender.data.model.api.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("token")
    fun getToken(@Field("grant_type") grant_type:String, @Field("username") username:String, @Field("password") password:String ):Call<GetTokenAPI>

    //location
    @GET("api/locations")
    fun getLocationList(@Header ("Authorization") Token:String):Call<List<LocationModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/location")
    fun createLocation(@Header ("Authorization") Token:String, @Body  newLocation: LocationModelAPI):Call<LocationModelAPI>

    //status
    @GET("api/status")
    fun getStatusList(@Header ("Authorization") Token:String):Call<List<StatusModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/status")
    fun CreateStatus(@Header ("Authorization") Token:String, @Body newStatus:StatusModelAPI):Call<StatusModelAPI>
    //manu
    @GET("api/manufacturers")
    fun getManufacturerList(@Header ("Authorization") Token:String):Call<List<ManufacturerModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/manufacturer")
    fun CreateManufacturer(@Header ("Authorization") Token:String, @Body newStatus:ManufacturerModelAPI):Call<ManufacturerModelAPI>

    @GET("/api/bids")
    fun getBidList(@Header ("Authorization") Token:String):Call<List<BidModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("/api/bid")
    fun CreateBid(@Header ("Authorization") Token:String, @Body newBid:BidModelAPI):Call<BidModelAPI>

    @Headers("Content-Type: application/json")
    @PUT("/api/bid")
    fun UpdateBidWin(@Header ("Authorization") Token:String, @Body newBid:BidModelAPI):Call<BidModelAPI>


    //car models
    @GET("api/carmodels")
    fun getCarModelList(@Header ("Authorization") Token:String):Call<List<CarModelApi>>

    @Headers("Content-Type: application/json")
    @POST("api/carmodel")
    fun CreateCarModel(@Header ("Authorization") Token:String, @Body newCarModel: CarModelApi):Call<CarModelApi>
    //tender
    @GET("api/tenders")
    fun getTenderList(@Header ("Authorization") Token:String):Call<List<TenderModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/tender")
    fun CreateTender(@Header ("Authorization") Token:String, @Body newTenderModelAPI: TenderModelAPI):Call<TenderModelAPI>

    @Headers("Content-Type: application/json")
    @PUT("/api/tender")
    fun updateTender(@Header ("Authorization") Token:String, @Body newTenderModelAPI: TenderModelAPI):Call<TenderModelAPI>



    //stock
    @GET("api/stocks")
    fun getCarStockList(@Header ("Authorization") Token:String):Call<List<StockInfoModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("api/stock")
    fun CreateCarStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: StockInfoModelAPI):Call<StockInfoModelAPI>

    @Headers("Content-Type: application/json")
    @PUT("/api/stock")
    fun updateCarStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPIAPI: StockIDModelAPI):Call<StockInfoModelAPI>

    @GET("/api/tenderstocks")
    fun getTenderStock(@Header ("Authorization") Token:String):Call<List<TenderStockModelAPI>>

    @Headers("Content-Type: application/json")
    @PUT("/api/tenderstock")
    fun updateTenderStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: TenderStockModelAPI):Call<TenderStockModelAPI>

    @Headers("Content-Type: application/json")
    @POST("api/tenderstock")
    fun CreateTenderStock(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: TenderStockModelAPI):Call<TenderStockModelAPI>

    @GET("/api/tenderusers")
    fun getTenderUser(@Header ("Authorization") Token:String):Call<List<TenderUserModelAPI>>

    @Headers("Content-Type: application/json")
    @POST("/api/tenderuser")
    fun CreateTenderUser(@Header ("Authorization") Token:String, @Body newStockInfoModelAPI: TenderUserModelAPI):Call<TenderUserModelAPI>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("api/users")
    fun getListUser(@Header ("Authorization") Token:String):Call<List<UserProfilAPI>>

    @GET("api/user?")
    fun getUserProfil(@Header ("Authorization") Token:String, @Query("email") useremail:String):Call<UserProfilAPI>

    @Headers("Content-Type: application/json")
    @POST("api/user")
    fun createNewUser(@Header ("Authorization") Token:String, @Body newUserProfilApi: CreateNewUserAPI):Call<CreateNewUserAPI>

    @Headers("Content-Type: application/json")
    @PUT("/api/user")
    fun updateUser(@Header("Authorization") Token:String, @Body newUserProfilApi: UserProfilAPI):Call<UserProfilAPI>

    @GET("api/roles")
    fun getUserRols(@Header ("Authorization") Token:String):Call<List<UserRoleAPI>>

}