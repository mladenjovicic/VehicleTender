package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import com.mladenjovicic.vehicletender.data.model.db.StatusModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import retrofit2.Response

class AddTenderViewModel : ViewModel() {
    fun addTender(context: Context, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        dbRepository.insertDataTender(context, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
        addTenderJSON(context, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }

    fun getListStatus (context: Context): LiveData<List<StatusModelDB>>? {
        return dbRepository.getStatus(context)
    }

    fun addTenderJSON(context: Context, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
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

}