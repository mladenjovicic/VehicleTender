package com.mladenjovicic.vehicletender.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
        var userModelDB:LiveData<UserModelDB>?=null
        var locationModelDB:LiveData<LocationModelDB>?= null
        lateinit var liveDataList: MutableLiveData<List<LocationModelAPI>>

        fun checkUser(context: Context, email:String, password:String):LiveData<UserModelDB>?{
            userModelDB =  dbRepository.getUserDate(context, email, password)
            return userModelDB
        }

        fun addNewUser(context: Context,uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        dbRepository.insertDataUser(context,uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
        }

        fun checkTableUser(context: Context):LiveData<UserModelDB>?{
            userModelDB = dbRepository.checkTableUser(context)
            return userModelDB
        }

        fun checkTableLocation(context: Context):LiveData<LocationModelDB>?{
            locationModelDB =dbRepository.checkTableLocation(context)
            return locationModelDB
        }

        fun addLocationList(context: Context, city:String, zip:String){
            dbRepository.insertDataLocation(context, city, zip)
        }
        fun addCarList(context: Context, car:String){
            dbRepository.insertDataManafactura(context, car)
        }

        fun addTenderStatus(context: Context,statusType:String ){
            dbRepository.insertStatus(context, statusType)
        }



        fun parsetJSONLocation(context: Context){

            val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val call = service.getLocationList()
                call?.enqueue(object : Callback<List<LocationModelAPI>>{
                    override fun onResponse(
                        call: Call<List<LocationModelAPI>>,
                        response: Response<List<LocationModelAPI>>
                    ) {
                        val body = response.body()
                        if(body!= null){
                            for (i in 0 until  body.count()){
                                println("test 12 " + body[i].city)
                            }
                        }

                    }
                    override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                        println(t.localizedMessage)
                    }
                })
            }
        }

        fun parsetJSONStatus(context: Context){
            val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val call = service.getStatusList()
                    call?.enqueue(object : Callback<List<StatusModelAPI>>{
                        override fun onResponse(call: Call<List<StatusModelAPI>>, response: Response<List<StatusModelAPI>>) {
                            val body = response.body()
                            if(body!= null){
                                for (i in 0 until  body.count()){
                                    println("test 12 " + body[i].city)
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