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
import com.mladenjovicic.vehicletender.data.model.api.DefaultResponse
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
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

class AddLocationViewModel : ViewModel() {

    fun addNewLocation(context: Context, newLocation:String, zipCode:String){
        if(newLocation.isEmpty()){
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }else{
            dbRepository.insertDataLocation(context,newLocation,zipCode)
        }
    }
    fun getListLocation (context: Context):LiveData<List<LocationModelDB>>{
        return dbRepository.getListLocation(context)!!
    }

    fun addLocationJSON(){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.createNewLocation(2,"test", "test2").enqueue(object : Callback<DefaultResponse>, retrofit2.Callback<DefaultResponse> {
            override fun onResponse(call: retrofit2.Call<DefaultResponse>, response: Response<DefaultResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: retrofit2.Call<DefaultResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })






      /*  val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        val newLocation = LocationModelAPI(2, "BG", "10000")
        val requestCall = service.createLocation(newLocation)
        requestCall.enqueue(object: Callback<LocationModelAPI>, retrofit2.Callback<LocationModelAPI> {
            override fun onResponse(call: retrofit2.Call<LocationModelAPI>, response: Response<LocationModelAPI>) {
                }

            override fun onFailure(call: retrofit2.Call<LocationModelAPI>, t: Throwable) {
                println("Failed to add item")
            }


        })*/



       /* val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        val jsonObject = JSONObject()
        jsonObject.put("id", 2)
        jsonObject.put("City", "BG")
        jsonObject.put("ZipCode" , "11000")
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.createLocation(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                            JsonParser.parseString(response.body()?.toString()))
                    Log.d("Pretty Printed JSON :", prettyJson)
                }else{
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
        }

        }

    }*/

}}