package com.mladenjovicic.vehicletender.data.repository.api

import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI

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