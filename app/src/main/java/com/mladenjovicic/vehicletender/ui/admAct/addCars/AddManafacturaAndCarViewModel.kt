package com.mladenjovicic.vehicletender.ui.admAct.addCars

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import retrofit2.Response

class AddManafacturaAndCarViewModel : ViewModel() {
    fun addManufacturer(context: Context, manufacturer_name:String ){
        if(manufacturer_name.isEmpty()){
            Toast.makeText(context, "", Toast.LENGTH_SHORT)
        }else{
            dbRepository.insertDataManafactura(context,999,manufacturer_name)
        }

    }

    fun getListManafactura(context: Context):LiveData<List<ManufacturerModelDB>>{
        return dbRepository.getDataManafactura(context)!!
    }

    fun addCar(context: Context, id:Int?, model_name:String,model_no:String, manufacturer_id:Int ){
            if(model_name.isNotEmpty() && model_no.isNotEmpty() && manufacturer_id > 0){
                dbRepository.insertDataCar(context, 999,model_name, model_no, manufacturer_id)
            }else {
                Toast.makeText(context, "Sva polja moraju biti popunjena ", Toast.LENGTH_SHORT).show()
            }

    }

    fun addCarModelJSON(id:Int?, model_name:String,model_no:String, manufacturer_id:Int ){
        val service = RetrofitInstance.getRetrofit().create(RetrofitInterface::class.java)
        service.CreateCarModel(CarModelApi(id,model_name, model_no, manufacturer_id)).enqueue(object : retrofit2.Callback<CarModelApi> {
            override fun onResponse(call: retrofit2.Call<CarModelApi>, response: Response<CarModelApi>) {
                var newlyCreatedDestination = response.body()
                if(response.code() == 201){
                    println("Successfully Added"+newlyCreatedDestination.toString())
                }else{
                    Log.d("error post json",  "error create locatio ${response.code()}")
                }}

            override fun onFailure(call: retrofit2.Call<CarModelApi>, t: Throwable) {
                Log.d("error post json", "error post json ${t.localizedMessage}")
            }
        })
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
}