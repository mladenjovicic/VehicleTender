package com.mladenjovicic.vehicletender.ui.admAct.addUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AddUserViewModel : ViewModel() {
    var listLocation :LiveData<List<LocationModelDB>>?= null
    fun addNewUser(context: Context, uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        dbRepository.insertDataUser(context,uuid,contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }

    fun getListLocation (context: Context): LiveData<List<LocationModelDB>>? {
        return dbRepository.getListLocation(context)!!
    }
}