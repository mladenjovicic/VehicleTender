package com.mladenjovicic.vehicletender.ui.updateUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CreateNewUserAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderStockModelAPI
import com.mladenjovicic.vehicletender.data.model.api.UserProfilAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.RoleDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.UserUpdateRespository

class UserUpdateViewModel(private val userUpdateRepositror: UserUpdateRespository) : ViewModel() {
    var userModelDB:LiveData<UserModelDB>?=null
    val requestState = MutableLiveData<RequestState>()
    lateinit var updateUserProfil: MutableLiveData<UserProfilAPI?>

    init {
        updateUserProfil = MutableLiveData()
    }

    fun getUserDateID(uuid:String): LiveData<UserModelDB>? {
        userModelDB =  userUpdateRepositror.getUserDateID(uuid)
        return  userModelDB
    }

    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return userUpdateRepositror.getListLocation()!!
    }

    fun updateUser(
            uuid:String,
            contact_name:String,
            contact_surname:String,
            email:String,
            password:String,
            status_user:Int,
            id_location:String,
            phone:String,
            company_name:String){
        userUpdateRepositror.updateUser(uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }

    fun getListUserRole():LiveData<List<RoleDB>>?{
        return userUpdateRepositror.readUserRole()
    }

    fun updateUserJSON(
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
        userUpdateRepositror.updateUserJSON(
                token,
                Id,
                Email,
                UserName,
                LocationId,
                isActive,
                FirstName,
                LastName,
                PhoneNumber,
                RoleName,
                RoleId,
                CompanyName,
                password,
                updateUserProfil,
                requestState)
    }
    fun getUpdateUserObserver(): MutableLiveData<UserProfilAPI?> {
        return updateUserProfil
    }
}