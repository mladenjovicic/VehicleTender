package com.mladenjovicic.vehicletender.ui.admAct.addUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CreateNewUserAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.RoleDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository

class AddUserViewModel(private val addNewUserRepositror: AdminRepository) : ViewModel() {
    var listLocation :LiveData<List<LocationModelDB>>?= null
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewUserAPI: MutableLiveData<CreateNewUserAPI?>

    init{
        createNewUserAPI = MutableLiveData()
    }

    fun addNewUser(uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        addNewUserRepositror.addNewUser(uuid,contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }

    @JvmName("getListLocation1")
    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return addNewUserRepositror.getListLocation()
    }

    fun getListUserRole():LiveData<List<RoleDB>>?{
        return addNewUserRepositror.readUserRole()
    }

    fun createNewUser(

        token:String,
        Id:String?,
        Email:String,
        UserName : String?,
        LocationId:Int,
        isActive : Boolean,
        FirstName:String,
        LastName : String,
        PhoneNumber:String,
        RoleName : String,
        RoleId:String,
        CompanyName : String,
        password: String

    ){
        addNewUserRepositror.createNewUserJSON(token, Id, Email, UserName, LocationId, isActive, FirstName, LastName, PhoneNumber, RoleName, RoleId, CompanyName, password, createNewUserAPI, requestState)
    }
}