package com.mladenjovicic.vehicletender.data.API

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.db.RoomDB
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RetrofitService(private val retrofitInstance: RetrofitInstanceN) {


    private val retrofitInterface = retrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

    fun addLocationJSON(id:Int?, city:String, zipCode: String){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.createLocation(LocationModelAPI(id,city, zipCode)).enqueue(object : retrofit2.Callback<LocationModelAPI> {
            override fun onResponse(call: retrofit2.Call<LocationModelAPI>, response: Response<LocationModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.code() == 201){
                    println("Successfully Added"+newlyCreatedDestination.toString())

                }else {
                    Log.d("error post json", "error create locatio ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<LocationModelAPI>, t: Throwable) {
                Log.d("error post json", "error post json ${t.localizedMessage}")
            }
        })
    }


    fun addManufacturerJSON(manufacturerModelAPI: ManufacturerModelAPI,
                            liveData: MutableLiveData<ManufacturerModelAPI>,
                            requestState: MutableLiveData<RequestState>){

        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.CreateManufacturer(manufacturerModelAPI)
        call.enqueue(object :Callback<ManufacturerModelAPI>{
            override fun onResponse(
                call: Call<ManufacturerModelAPI>,
                response: Response<ManufacturerModelAPI>
            ) {
                var body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)
            }

            override fun onFailure(call: Call<ManufacturerModelAPI>, t: Throwable) {
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


    fun addCarModelJSON(id:Int?, ModelName:String,ModelNO:String, ManufacturerId:Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateCarModel(CarModelApi(id,ModelName, ModelNO, ManufacturerId)).enqueue(object : retrofit2.Callback<CarModelApi> {
            override fun onResponse(call: retrofit2.Call<CarModelApi>, response: Response<CarModelApi>) {
                var newlyCreatedDestination = response.body()
                if(response.code() == 201){
                    println("Successfully Added"+newlyCreatedDestination.toString())
                }else{
                    Log.d("error post json",  "error create locatio ${response.code()}")
                }
            }
            override fun onFailure(call: retrofit2.Call<CarModelApi>, t: Throwable) {
                Log.d("error post json", "error post json ${t.localizedMessage}")
            }
        })
    }


    fun addTenderJSON(context: Context, createdDate:String, createdBy:String, tenderNo:String, openDate:String, closeDate:String, statusId:Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

        service.CreateTender(TenderModelAPI(0, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)).enqueue(object : retrofit2.Callback<TenderModelAPI> {
            override fun onResponse(call: retrofit2.Call<TenderModelAPI>, response: Response<TenderModelAPI>) {
                var newlyCreatedDestination = response.body() // Use it or ignore it
                if(response.code() == 201){
                    println("Successfully Added" + newlyCreatedDestination.toString())
                }else {
                    Log.d("error post json", "error create locatio ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<TenderModelAPI>, t: Throwable) {
                Log.d("Error Tender Insert", "Error Tender Insert "+ t.localizedMessage )
            }
        })
    }


    fun readLocaitonJSON(liveData: MutableLiveData<List<LocationModelAPI>>,
                      requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getLocationList()
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

    fun readStatusJSON(liveData: MutableLiveData<List<StatusModelAPI>>,
                         requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getStatusList()
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

    fun readCarModelJSON(liveData: MutableLiveData<List<CarModelApi>>,
                       requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getCarModelList()
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
    fun readManufacturerJSON(liveData: MutableLiveData<List<ManufacturerModelAPI>>,
                         requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getManufacturerList()
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
    fun readCarStockJSON(liveData: MutableLiveData<List<StockInfoModelAPI>>,
                             requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getCarStockList()
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
    fun readTenderJSON(liveData: MutableLiveData<List<TenderModelAPI>>,
                             requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderList()
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
    fun readBidJSON(liveData: MutableLiveData<List<BidModelAPI>>,
                             requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getBidList()
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

    fun readTenderStockJSON(liveData: MutableLiveData<List<TenderStockModelAPI>>,
                    requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderStock()
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

    fun readTenderUserJSON(liveData: MutableLiveData<List<TenderUserModelAPI>>,
                            requestState: MutableLiveData<RequestState>
    ){
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getTenderUser()
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
}