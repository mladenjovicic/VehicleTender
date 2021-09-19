package com.mladenjovicic.vehicletender.ui.updateUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import com.mladenjovicic.vehicletender.data.repository.UserUpdateRespository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class UserUpdateViewModel(private val userUpdateRepositror: UserUpdateRespository) : ViewModel() {
    var userModelDB:LiveData<UserModelDB>?=null

    fun getUserDateID(uuid:String): LiveData<UserModelDB>? {
        userModelDB =  userUpdateRepositror.getUserDateID(uuid)
        return  userModelDB
    }

    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return userUpdateRepositror.getListLocation()!!
    }

    fun updateUser(uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        userUpdateRepositror.updateUser(uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }
}