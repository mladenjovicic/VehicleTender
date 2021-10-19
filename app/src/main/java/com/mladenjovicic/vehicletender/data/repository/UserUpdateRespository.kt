package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CreateNewUserAPI
import com.mladenjovicic.vehicletender.data.model.api.UserProfilAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.RoleDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class UserUpdateRespository(private val retrofitService: RetrofitService,
                            private val localRepository: LocalRepository
) {

    fun getUserDateID(uuid:String): LiveData<UserModelDB>? {
        return localRepository.getUserDateID(uuid)
    }

    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return localRepository.getListLocation()!!
    }

    fun updateUser(uuid:String, contact_name:String, contact_surname:String, email:String, password:String, status_user:Int, id_location:String, phone:String, company_name:String){
        localRepository.insertDataUser(uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }
    fun readUserRole():LiveData<List<RoleDB>>?{
        return  localRepository.readRole()
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
            password: String,
            liveData: MutableLiveData<UserProfilAPI?>,
            requestState: MutableLiveData<RequestState>){
        retrofitService.updateUser(token, Id, Email, UserName, LocationId, isActive, FirstName, LastName, PhoneNumber, RoleName, RoleId, CompanyName, password, liveData, requestState)
    }
}