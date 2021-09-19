package com.mladenjovicic.vehicletender.ui.admAct.addLocation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.api.DefaultResponse
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import java.util.*
import retrofit2.*


class AddLocationViewModel(private val AddLocationRepository: AdminRepository) : ViewModel() {

        fun addNewLocation( newLocation:String, zipCode:String){
            if(newLocation.isEmpty()){

            }else{
                AddLocationRepository.addNewLocation(999,newLocation,zipCode)
            }
        }
        fun getListLocation ():LiveData<List<LocationModelDB>>{
            return AddLocationRepository.getListLocation()!!
        }

     fun addLocationJSON(id:Int?, city:String, zipCode: String){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.createLocation(LocationModelAPI(id,city, zipCode)).enqueue(object : retrofit2.Callback<LocationModelAPI> {
            override fun onResponse(call: retrofit2.Call<LocationModelAPI>, response: Response<LocationModelAPI>) {
                var newlyCreatedDestination = response.body() // Use it or ignore it
               println("Successfully Added"+newlyCreatedDestination.toString() + " " + response.code())
            }

            override fun onFailure(call: retrofit2.Call<LocationModelAPI>, t: Throwable) {
                println("noup" +t.localizedMessage)
            }
        })
}

}