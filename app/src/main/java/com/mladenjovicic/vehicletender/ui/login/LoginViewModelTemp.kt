package com.mladenjovicic.vehicletender.ui.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.LoginRepository


class LoginViewModelTemp(private val loginRepository: LoginRepository) : ViewModel() {

    var userModelDB: LiveData<UserModelDB>? = null
    var locationModelDB: LiveData<LocationModelDB>? = null

    val locationsLiveData = MutableLiveData<List<LocationModelAPI>>()
    val requestState = MutableLiveData<RequestState>()

    init {
        getLocations()
    }
    fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
        return loginRepository.checkUser(email, password)
    }
    fun addNewUser(
            uuid: String,
            contact_name: String,
            contact_surname: String,
            email: String,
            password: String,
            status_user: Int,
            id_location: String,
            phone: String,
            company_name: String
    ) {
        loginRepository.addNewUser(
                uuid,
                contact_name,
                contact_surname,
                email,
                password,
                status_user,
                id_location,
                phone,
                company_name
        )
    }

    fun checkTableUser(): LiveData<UserModelDB>? {
        userModelDB = loginRepository.checkTableUser()
        return userModelDB
    }

    fun checkTableLocation(): LiveData<LocationModelDB>? {
        locationModelDB = loginRepository.checkTableLocation()
        return locationModelDB
    }

    fun addLocationList(city: String, zip: String) {
        loginRepository.addLocationList(city, zip)
    }

    fun addCarList(car: String) {
        loginRepository.addCarList(car)
    }

    fun addTenderStatus(statusType: String) {
        loginRepository.addTenderStatus(statusType)
    }

    private fun getLocations() {
        loginRepository.getLocations(locationsLiveData, requestState)
    }


}