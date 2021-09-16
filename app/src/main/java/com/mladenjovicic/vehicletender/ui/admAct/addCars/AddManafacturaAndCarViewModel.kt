package com.mladenjovicic.vehicletender.ui.admAct.addCars

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AddManafacturaAndCarViewModel : ViewModel() {
    fun addManufacturer(context: Context, manufacturer_name:String ){
        if(manufacturer_name.isEmpty()){
            Toast.makeText(context, "", Toast.LENGTH_SHORT)
        }else{
            dbRepository.insertDataManafactura(context,manufacturer_name)
        }

    }

    fun getListManafactura(context: Context):LiveData<List<ManufacturerModelDB>>{
        return dbRepository.getDataManafactura(context)!!
    }

    fun addCar(context: Context, model_name:String,model_no:String, manufacturer_id:Int ){
            if(model_name.isNotEmpty() && model_no.isNotEmpty() && manufacturer_id > 0){
                dbRepository.insertDataCar(context, model_name, model_no, manufacturer_id)
            }else {
                Toast.makeText(context, "Sva polja moraju biti popunjena ", Toast.LENGTH_SHORT).show()
            }

    }
}