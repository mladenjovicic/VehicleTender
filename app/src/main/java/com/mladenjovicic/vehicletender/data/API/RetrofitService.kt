package com.mladenjovicic.vehicletender.data.API

import android.content.Context
import android.util.Log
import com.mladenjovicic.vehicletender.data.db.RoomDB
import com.mladenjovicic.vehicletender.data.model.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class RetrofitService(private val retrofitInstance: RetrofitInstance) {

    

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
    fun parsetJSONLocation(context: Context, sizeLocation:Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getLocationList()
            call?.enqueue(object : retrofit2.Callback<List<LocationModelAPI>> {
                override fun onResponse(
                    call: Call<List<LocationModelAPI>>,
                    response: Response<List<LocationModelAPI>>
                ) {
                    val body = response.body()
                    if(body!= null){
                        if(body.count()>sizeLocation){
                            for (i in 0 until  body.count()){

                            }
                        }}
                }
                override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })
        }
    }

    fun addManufacturerJSON(id:Int?, ManufacturerName:String){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateManufacturer(ManufacturerModelAPI(id,ManufacturerName)).enqueue(object : retrofit2.Callback<ManufacturerModelAPI> {
            override fun onResponse(call: retrofit2.Call<ManufacturerModelAPI>, response: Response<ManufacturerModelAPI>) {
                var newlyCreatedDestination = response.body()
                if(response.code() == 201){
                    println("Successfully Added"+newlyCreatedDestination.toString())
                }else{
                    Log.d("error post json",  "error create locatio ${response.code()}")
                }

            }
            override fun onFailure(call: retrofit2.Call<ManufacturerModelAPI>, t: Throwable) {
                Log.d("error post json", "error post json ${t.localizedMessage}")
            }
        })
    }
    fun parsetJSONManufacturer(context: Context, sizeManufacturer:Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getManufacturerList()
            call?.enqueue(object : retrofit2.Callback<List<ManufacturerModelAPI>> {
                override fun onResponse(
                    call: Call<List<ManufacturerModelAPI>>,
                    response: Response<List<ManufacturerModelAPI>>
                ) {
                    val body = response.body()
                    if(body!= null){
                        if(body.count()>sizeManufacturer){
                            for (i in 0 until  body.count()){


                            }
                        }
                    }

                }
                override fun onFailure(call: Call<List<ManufacturerModelAPI>>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })
        }
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
    fun parsetJSONCarModel(context: Context, sizeCarModel:Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getCarModelList()
            call?.enqueue(object : retrofit2.Callback<List<CarModelApi>> {
                override fun onResponse(call: Call<List<CarModelApi>>, response: Response<List<CarModelApi>>) {
                    val body = response.body()
                    if(body!= null){
                        if(body.count()>sizeCarModel){
                            for (i in 0 until  body.count()){
                                println("carModel"+body[i].ModelName)

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<CarModelApi>>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })

        }

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

    fun parsetJSONStatus(context: Context, sizeStatus : Int){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getStatusList()
            call?.enqueue(object : retrofit2.Callback<List<StatusModelAPI>> {
                override fun onResponse(call: Call<List<StatusModelAPI>>, response: Response<List<StatusModelAPI>>) {
                    val body = response.body()
                    if(body!= null){
                        if(body.count()>sizeStatus){
                            for (i in 0 until  body.count()){
                                println("status"+body[i].type)

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<StatusModelAPI>>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })

        }


    }

}