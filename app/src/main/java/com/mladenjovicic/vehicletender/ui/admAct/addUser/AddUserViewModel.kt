package com.mladenjovicic.vehicletender.ui.admAct.addUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AddUserViewModel(private val addNewUserRepositror: AdminRepository) : ViewModel() {
    var listLocation :LiveData<List<LocationModelDB>>?= null

    fun addNewUser(uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        addNewUserRepositror.addNewUser(uuid,contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }

    @JvmName("getListLocation1")
    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return addNewUserRepositror.getListLocation()
    }
}