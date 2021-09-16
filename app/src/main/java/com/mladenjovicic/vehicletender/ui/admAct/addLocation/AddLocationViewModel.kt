package com.mladenjovicic.vehicletender.ui.admAct.addLocation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

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

}