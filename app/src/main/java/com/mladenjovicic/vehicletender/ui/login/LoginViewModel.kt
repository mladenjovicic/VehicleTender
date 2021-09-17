package com.mladenjovicic.vehicletender.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
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

        fun addLocationList(context: Context, id:Int, city:String, zip:String){
            dbRepository.insertDataLocation(context, id, city, zip)
        }
        fun addCarList(context: Context,id:Int, car:String){
            dbRepository.insertDataManafactura(context,id, car)
        }

        fun addTenderStatus(context: Context,id:Int, statusType:String ){
            dbRepository.insertStatus(context, id, statusType)
        }

        fun addCarModel(context: Context, id:Int, modelName:String, modelNo:String, manufcaterId:Int){
            dbRepository.insertDataCar(context,id, modelName,modelNo,manufcaterId  )
        }



        fun parsetJSONLocation(context: Context, sizeLocation:Int){
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
                            if(body.count()>sizeLocation){
                            for (i in 0 until  body.count()){
                                addLocationList(context,body[i].id!!, body[i].city!!, body[i].zipCOde!!)
                            }
                        }}
                    }
                    override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                        println(t.localizedMessage)
                    }
                })
            }
        }

        fun parsetJSONManufacturer(context: Context, sizeManufacturer:Int){
              val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
           CoroutineScope(Dispatchers.IO).launch {
              val call = service.getManufacturerList()
              call?.enqueue(object : Callback<List<ManufacturerModelAPI>>{
                  override fun onResponse(
                      call: Call<List<ManufacturerModelAPI>>,
                      response: Response<List<ManufacturerModelAPI>>
                  ) {
                      val body = response.body()
                      if(body!= null){
                          if(body.count()>sizeManufacturer){
                          for (i in 0 until  body.count()){

                              addCarList(context, body[i].ID!!, body[i].ManufacturerName!!)
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

        fun parsetJSONStatus(context: Context, sizeStatus : Int){
            val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val call = service.getStatusList()
                    call?.enqueue(object : Callback<List<StatusModelAPI>>{
                        override fun onResponse(call: Call<List<StatusModelAPI>>, response: Response<List<StatusModelAPI>>) {
                            val body = response.body()
                            if(body!= null){
                                if(body.count()>sizeStatus){
                                for (i in 0 until  body.count()){
                                    println("status"+body[i].type)
                                    addTenderStatus(context, body[i].id!!,body[i].type!!)
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

        fun parsetJSONCarModel(context: Context, sizeCarModel:Int){
            val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val call = service.getCarModelList()
                call?.enqueue(object : Callback<List<CarModelApi>>{
                    override fun onResponse(call: Call<List<CarModelApi>>, response: Response<List<CarModelApi>>) {
                        val body = response.body()
                        if(body!= null){
                            if(body.count()>sizeCarModel){
                                for (i in 0 until  body.count()){
                                    println("carModel"+body[i].ModelName)
                                    addCarModel(context,body[i].ID!!,body[i].ModelName!!, body[i].ModelNO!!, body[i].ManufacturerId!!)
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

}