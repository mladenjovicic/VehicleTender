package com.mladenjovicic.vehicletender.repository.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.API.VTAPIServiceInterface
import com.mladenjovicic.vehicletender.API.VTApiInstance
import com.mladenjovicic.vehicletender.model.api.LocationModelAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepository {
    var listLocation: MutableLiveData<List<LocationModelAPI>>?=null

    /*fun getLocationList():MutableLiveData<List<LocationModelAPI>>?{

        val service = VTApiInstance.getVTAPIInstance().create(VTAPIServiceInterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getLocationList()
            call?.enqueue(object : Callback<List<LocationModelAPI>> {
                override fun onResponse(
                    call: Call<List<LocationModelAPI>>,
                    response: Response<List<LocationModelAPI>>
                ) {
                    val body = response.body()
                    if(body!= null){
                        for (i in 0 until  body.count()){
                            println("test 12 " + body[i].city)
                            var LocationModelAPI = LocationModelAPI(body[i].id, body[i].city, body[i].zipCOde)
                            listLocation!!.value(LocationModelAPI)
                        }
                    }else{
                        //println("greska" + call.c.toString())
                    }

                }
                override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                    println("test123"+t.localizedMessage)
                }
            })}



        return listLocation
    }*/
}