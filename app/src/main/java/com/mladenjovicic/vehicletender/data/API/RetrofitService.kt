package com.mladenjovicic.vehicletender.data.API

import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
//import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RetrofitService(private val retrofitInstance: RetrofitInstanceN) {

    private val retrofitInterface = retrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

    fun addLocationJSON(
        token:String,
        id:Int?,
        city:String,
        zipCode: String,
        requestState: MutableLiveData<RequestState>,
        liveData: MutableLiveData<LocationModelAPI?>){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.createLocation(token, LocationModelAPI(id,city, zipCode)).enqueue(object : retrofit2.Callback<LocationModelAPI> {
            override fun onResponse(call: retrofit2.Call<LocationModelAPI>, response: Response<LocationModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body()!=null){
                println("Successfully Added"+newlyCreatedDestination.toString()+ " "+ response.code())
                requestState.postValue(RequestState.success)
                liveData.postValue(LocationModelAPI(response.body()!!.Id, response.body()!!.City, response.body()!!.ZipCode))
                }else{
                    Log.d("error post json", "error create locatio ${response.code()}")
                }
            }
            override fun onFailure(call: retrofit2.Call<LocationModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun addManufacturerJSON(
        token: String,
        id:Int?,
        ManufacturerName:String,
        liveData: MutableLiveData<ManufacturerModelAPI?>,
        requestState: MutableLiveData<RequestState>){

        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateManufacturer(token, ManufacturerModelAPI(id,ManufacturerName)).enqueue(object : retrofit2.Callback<ManufacturerModelAPI> {
            override fun onResponse(call: retrofit2.Call<ManufacturerModelAPI>, response: Response<ManufacturerModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body()!=null){
                    println("Successfully Added Manufacturer"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(ManufacturerModelAPI(response.body()!!.ID, response.body()!!.ManufacturerName))
                }else{
                    liveData.postValue(null)
                    Log.d("error post json", "error create Manufacturer ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }

            }
            override fun onFailure(call: retrofit2.Call<ManufacturerModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun addCarModelJSON(
        token: String,
        id:Int?,
        ModelName:String,
        ModelNO:String,
        ManufacturerId:Int,
        liveData: MutableLiveData<CarModelApi?>,
        requestState: MutableLiveData<RequestState>
    ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateCarModel(token, CarModelApi(id,ModelName, ModelNO, ManufacturerId)).enqueue(object : retrofit2.Callback<CarModelApi> {
            override fun onResponse(call: retrofit2.Call<CarModelApi>, response: Response<CarModelApi>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added CarModel "+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(CarModelApi(response.body()!!.ID, response.body()!!.ModelName, response.body()!!.ModelNO, response.body()!!.ManufacturerId))
                }else {
                    liveData.postValue(null)
                    Log.d("error post json", "error create CarModel ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }
            override fun onFailure(call: retrofit2.Call<CarModelApi>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun addTenderJSON(
        token: String,
        id: Int?,
        createdDate:String,
        createdBy:String,
        tenderNo:String,
        openDate:String,
        closeDate:String,
        statusId:Int,
        liveData: MutableLiveData<TenderModelAPI?>,
        requestState: MutableLiveData<RequestState>){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.CreateTender(token, TenderModelAPI(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)).enqueue(object : retrofit2.Callback<TenderModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderModelAPI>, response: Response<TenderModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added Tender" +newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(TenderModelAPI(response.body()!!.id, response.body()!!.createdDate,response.body()!!.createdBy, response.body()!!.tenderNo, response.body()!!.openDate, response.body()!!.closeDate, response.body()!!.statusId))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create Tender ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun  updateTenderJSON(
            token: String,
            id: Int?,
            createdDate:String,
            createdBy:String,
            tenderNo:String,
            openDate:String,
            closeDate:String,
            statusId:Int,
            liveData: MutableLiveData<TenderModelAPI?>,
            requestState: MutableLiveData<RequestState>){
        val call = retrofitInterface.updateTender(token, TenderModelAPI(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId))
        call.enqueue(object : retrofit2.Callback<TenderModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderModelAPI>, response: Response<TenderModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully update tender "+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(TenderModelAPI(response.body()!!.id, response.body()!!.createdDate,response.body()!!.createdBy, response.body()!!.tenderNo, response.body()!!.openDate, response.body()!!.closeDate, response.body()!!.statusId))
                }else {
                    liveData.postValue(null)
                    Log.d("error put json", "error update tender  ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderModelAPI>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })
    }

    fun addCarStockJSON(
        token: String,
        id: Int?,
        year:Int,
        modelLineId:Int,
        mileage:Int,
        price:Double,
        comments:String,
        locationId:Int,
        regNo:String,
        isSold:Boolean,
        liveData: MutableLiveData<StockInfoModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.CreateCarStock(token, StockInfoModelAPI(id, year, modelLineId, mileage, price, comments, locationId, regNo, isSold)).enqueue(object : retrofit2.Callback<StockInfoModelAPI> {
            override fun onResponse(call: retrofit2.Call<StockInfoModelAPI>, response: Response<StockInfoModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added Car Stock"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(StockInfoModelAPI(response.body()!!.id, response.body()!!.year,response.body()!!.modelLineId, response.body()!!.mileage, response.body()!!.price, response.body()!!.comments,
                        response.body()!!.locationId,response.body()!!.regNo,response.body()!!.isSold))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create Car Stock ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }
            override fun onFailure(call: retrofit2.Call<StockInfoModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }
    fun updateCarStockJSON(
        token: String,
        id: Int,
        isSold:Boolean,
        liveData: MutableLiveData<StockInfoModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.updateCarStock(token, StockIDModelAPI(id,isSold)).enqueue(object : retrofit2.Callback<StockInfoModelAPI> {
            override fun onResponse(call: retrofit2.Call<StockInfoModelAPI>, response: Response<StockInfoModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully update carStock"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(StockInfoModelAPI(response.body()!!.id, response.body()!!.year,response.body()!!.modelLineId, response.body()!!.mileage, response.body()!!.price, response.body()!!.comments,
                        response.body()!!.locationId,response.body()!!.regNo,response.body()!!.isSold))
                }else {

                    liveData.postValue(null)
                    Log.d("error put json", "error update carStock ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }
            override fun onFailure(call: retrofit2.Call<StockInfoModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }
    fun addTenderStockJSON(
        token: String,
        id: Int?,
        stockId:Int,
        tenderId:Int,
        saleDate:String,
        isDeleted:Boolean,
        liveData: MutableLiveData<TenderStockModelAPI?>,
        requestState: MutableLiveData<RequestState>){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.CreateTenderStock(token, TenderStockModelAPI(id, stockId,tenderId,saleDate, isDeleted)).enqueue(object : retrofit2.Callback<TenderStockModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderStockModelAPI>, response: Response<TenderStockModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added Tender Stock"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(TenderStockModelAPI(response.body()!!.id, response.body()!!.stockId,response.body()!!.tenderId, response.body()!!.saleDate, response.body()!!.isDeleted))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create Tender Stock ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderStockModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun updateTenderStockJSON(
            token: String,
            id: Int?,
            stockId:Int,
            tenderId:Int,
            saleDate:String,
            isDeleted:Boolean,
            liveData: MutableLiveData<TenderStockModelAPI?>,
            requestState: MutableLiveData<RequestState>){


        val call = retrofitInterface.updateTenderStock(token, TenderStockModelAPI(id, stockId,tenderId,saleDate, isDeleted))


        call.enqueue(object : retrofit2.Callback<TenderStockModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderStockModelAPI>, response: Response<TenderStockModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully update tender stock "+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(TenderStockModelAPI(response.body()!!.id, response.body()!!.stockId,response.body()!!.tenderId, response.body()!!.saleDate, response.body()!!.isDeleted))
                }else {

                    liveData.postValue(null)
                    Log.d("error put json", "error update tender stock ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderStockModelAPI>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })
    }

    fun addBidJSON(
        token:String,
        id: Int?,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean?,
        isActive:Boolean?,
        liveData: MutableLiveData<BidModelAPI?>,
        requestState: MutableLiveData<RequestState>){

        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.CreateBid(token, BidModelAPI(id, TUserId,TStockId,Price, IsWinningPrice, isActive)).enqueue(object : retrofit2.Callback<BidModelAPI> {
            override fun onResponse(call: retrofit2.Call<BidModelAPI>, response: Response<BidModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added bid "+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(BidModelAPI(response.body()!!.ID, response.body()!!.TUserId,response.body()!!.TStockId, response.body()!!.Price, response.body()!!.IsWinningPrice, response.body()!!.isActive))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create bid ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<BidModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun UpdateBidJSON(
        token:String,
        id: Int?,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean?,
        isActive:Boolean?,
        liveData: MutableLiveData<BidModelAPI?>,
        requestState: MutableLiveData<RequestState>){

        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.UpdateBidWin(token, BidModelAPI(id, TUserId,TStockId,Price, IsWinningPrice, isActive)).enqueue(object : retrofit2.Callback<BidModelAPI> {
            override fun onResponse(call: retrofit2.Call<BidModelAPI>, response: Response<BidModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added update bid win "+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(BidModelAPI(response.body()!!.ID, response.body()!!.TUserId,response.body()!!.TStockId, response.body()!!.Price, response.body()!!.IsWinningPrice, response.body()!!.isActive))
                }else {

                    liveData.postValue(null)
                    Log.d("error put json", "error update bid win ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<BidModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun addTenderUserJSON(
        token: String,
        id: Int?,
        tenderId:Int,
        userId:String,
        liveData: MutableLiveData<TenderUserModelAPI?>,
        requestState: MutableLiveData<RequestState>){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateTenderUser(token,TenderUserModelAPI(id, tenderId,userId)).enqueue(object : retrofit2.Callback<TenderUserModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderUserModelAPI>, response: Response<TenderUserModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added tender user"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(TenderUserModelAPI(response.body()!!.id, response.body()!!.tenderId,response.body()!!.userId))
                }else {
                    liveData.postValue(null)
                    Log.d("error post json", "error create tender user ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderUserModelAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun readLocaitonJSON(
        token:String,
        liveData: MutableLiveData<List<LocationModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getLocationList(token)
        call.enqueue(object : Callback<List<LocationModelAPI>> {
            override fun onResponse(
                call: Call<List<LocationModelAPI>>,
                response: Response<List<LocationModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
    })

}

    fun readStatusJSON(
        token: String,
        liveData: MutableLiveData<List<StatusModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getStatusList(token)
        call.enqueue(object : Callback<List<StatusModelAPI>> {
            override fun onResponse(
                call: Call<List<StatusModelAPI>>,
                response: Response<List<StatusModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<StatusModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readCarModelJSON(
        token: String,
        liveData: MutableLiveData<List<CarModelApi>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getCarModelList(token)
        call.enqueue(object : Callback<List<CarModelApi>> {
            override fun onResponse(
                call: Call<List<CarModelApi>>,
                response: Response<List<CarModelApi>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<CarModelApi>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readManufacturerJSON(
        token: String,
        liveData: MutableLiveData<List<ManufacturerModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getManufacturerList(token)
        call.enqueue(object : Callback<List<ManufacturerModelAPI>> {
            override fun onResponse(
                call: Call<List<ManufacturerModelAPI>>,
                response: Response<List<ManufacturerModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<ManufacturerModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readCarStockJSON(
        token: String,
        liveData: MutableLiveData<List<StockInfoModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getCarStockList(token)
        call.enqueue(object : Callback<List<StockInfoModelAPI>> {
            override fun onResponse(
                call: Call<List<StockInfoModelAPI>>,
                response: Response<List<StockInfoModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<StockInfoModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readTenderJSON(
        token: String,
        liveData: MutableLiveData<List<TenderModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderList(token)
        call.enqueue(object : Callback<List<TenderModelAPI>> {
            override fun onResponse(
                call: Call<List<TenderModelAPI>>,
                response: Response<List<TenderModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<TenderModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readBidJSON(
        token: String,
        liveData: MutableLiveData<List<BidModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getBidList(token)
        call.enqueue(object : Callback<List<BidModelAPI>> {
            override fun onResponse(
                call: Call<List<BidModelAPI>>,
                response: Response<List<BidModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<BidModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun readTenderStockJSON(
        token: String,
        liveData: MutableLiveData<List<TenderStockModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderStock(token)
        call.enqueue(object : Callback<List<TenderStockModelAPI>> {
            override fun onResponse(
                call: Call<List<TenderStockModelAPI>>,
                response: Response<List<TenderStockModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<TenderStockModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }

    fun readTenderUserJSON(
        token: String,
        liveData: MutableLiveData<List<TenderUserModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderUser(token)
        call.enqueue(object : Callback<List<TenderUserModelAPI>> {
            override fun onResponse(
                call: Call<List<TenderUserModelAPI>>,
                response: Response<List<TenderUserModelAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<TenderUserModelAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })

    }
    fun getToken(
            username:String,
            password:String,
            liveData: MutableLiveData<GetTokenAPI?>,
            requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getToken("password", username,password)
        call.enqueue(object : Callback<GetTokenAPI> {
            override fun onResponse(
                    call: Call<GetTokenAPI>,
                    response: Response<GetTokenAPI>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<GetTokenAPI>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })

    }
    fun getListUser(
    Authorization:String,
    liveData: MutableLiveData<List<UserProfilAPI?>>,
    requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getListUser(Authorization)
        call.enqueue(object : Callback<List<UserProfilAPI>> {
            override fun onResponse(
                    call: Call<List<UserProfilAPI>>,
                    response: Response<List<UserProfilAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<UserProfilAPI>>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })

    }
    fun getUserProfil(
            Authorization:String,
            userEmail:String,
            liveData: MutableLiveData<UserProfilAPI?>,
            requestState: MutableLiveData<RequestState>){

        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getUserProfil(Authorization,userEmail)
        call.enqueue(object : Callback<UserProfilAPI> {
            override fun onResponse(
                    call: Call<UserProfilAPI>,
                    response: Response<UserProfilAPI>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<UserProfilAPI>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })

    }

    fun createNewUser(
        token:String,
        Id:String?,
        Email:String,
        UserName : String?,
        LocationId:Int,
        isActive : Boolean,
        FirstName:String,
        LastName : String,
        PhoneNumber:String,
        RoleName : String,
        RoleId:String,
        CompanyName : String,
        password: String,
        liveData: MutableLiveData<CreateNewUserAPI?>,
        requestState: MutableLiveData<RequestState>
    ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.createNewUser(token, CreateNewUserAPI(Id, Email,UserName, LocationId, isActive, FirstName, LastName, PhoneNumber, RoleName, RoleId, CompanyName, password)).enqueue(object : retrofit2.Callback<CreateNewUserAPI> {
            override fun onResponse(call: retrofit2.Call<CreateNewUserAPI>, response: Response<CreateNewUserAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully Added new user"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(CreateNewUserAPI(response.body()?.ID!!, response.body()!!.Email, response.body()?.UserName, response.body()!!.LocationId, response.body()!!.isActive, response.body()!!.FirstName,
                        response.body()!!.LastName, response.body()?.PhoneNumber, response.body()?.RoleName, response.body()?.RoleId, response.body()!!.DealerName, response.body()?.Password))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create new user ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: retrofit2.Call<CreateNewUserAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun updateUser(token:String,
                   Id:String?,
                   Email:String,
                   UserName : String?,
                   LocationId:Int,
                   isActive : Boolean,
                   FirstName:String,
                   LastName : String,
                   PhoneNumber:String,
                   RoleName : String,
                   RoleId:String,
                   CompanyName : String,
                   password: String,
                   liveData: MutableLiveData<UserProfilAPI?>,
                   requestState: MutableLiveData<RequestState>){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        /*service.updateUser(token, CreateNewUserAPI(Id, Email, UserName,LocationId, isActive,FirstName,LastName,PhoneNumber, RoleName,RoleId,CompanyName, password)).enqueue(object : retrofit2.Callback<CreateNewUserAPI>{*/
        val call = retrofitInterface.updateUser(token, UserProfilAPI(Id, Email, UserName,LocationId, isActive,FirstName,LastName,PhoneNumber, RoleName,RoleId,CompanyName)).enqueue(object : retrofit2.Callback<UserProfilAPI>{
            override fun onResponse(call: retrofit2.Call<UserProfilAPI>, response: Response<UserProfilAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.body() != null){
                    println("Successfully update user"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(UserProfilAPI(response.body()?.ID!!, response.body()!!.Email, response.body()?.UserName, response.body()!!.LocationId, response.body()!!.isActive, response.body()!!.FirstName,
                            response.body()!!.LastName, response.body()?.PhoneNumber, response.body()?.RoleName, response.body()?.RoleId, response.body()!!.CompanyName))
                }else {

                    liveData.postValue(null)
                    Log.d("error put json", "error update user ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: Call<UserProfilAPI>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }

        })




    }

    fun getUserRols(
        token: String,
        liveData: MutableLiveData<List<UserRoleAPI>>,
        requestState: MutableLiveData<RequestState>){

        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getUserRols(token)
        call.enqueue(object : Callback<List<UserRoleAPI>> {
            override fun onResponse(
                call: Call<List<UserRoleAPI>>,
                response: Response<List<UserRoleAPI>>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<UserRoleAPI>>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }
    fun getToken1(
        username:String,
        password:String,
        liveData: MutableLiveData<GetTokenAPI?>,
        requestState: MutableLiveData<RequestState>
    ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)


        service.getToken("password", username,password).enqueue(object : Callback<GetTokenAPI> {
            override fun onResponse(call: Call<GetTokenAPI>, response: Response<GetTokenAPI>) {
                val newlyCreatedDestination = response.body()
                if(response.code() == 201){
                    println("Successfully Added token"+newlyCreatedDestination.toString()+ " "+ response.code())
                    requestState.postValue(RequestState.success)
                    liveData.postValue(GetTokenAPI(response.body()!!.access_token, response.body()!!.token_type,response.body()!!.expires_in,response.body()!!.issued,response.body()!!.expires, response.body()!!.userName))
                }else {

                    liveData.postValue(null)
                    Log.d("error post json", "error create token ${response.code()}")
                    requestState.postValue(RequestState.failed)
                }
            }

            override fun onFailure(call: Call<GetTokenAPI>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

}