package com.mladenjovicic.vehicletender.ui.updateUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB
import com.mladenjovicic.vehicletender.repository.db.dbRepository

class UserUpdateViewModel : ViewModel() {
    var userModelDB:LiveData<UserModelDB>?=null

    fun getUserDateID(context: Context, uuid:String): LiveData<UserModelDB>? {
        userModelDB =  dbRepository.getUserDateID(context, uuid)
        return  userModelDB
    }

    fun getListLocation (context: Context): LiveData<List<LocationModelDB>>? {
        return dbRepository.getListLocation(context)!!
    }

    fun updateUser(context: Context,uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        dbRepository.insertDataUser(context,uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }
}