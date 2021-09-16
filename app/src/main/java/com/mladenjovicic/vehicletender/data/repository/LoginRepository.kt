package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.api.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepositoryTemp

class LoginRepository(private val retrofitService: RetrofitService,
                      private val localRepository: LocalRepositoryTemp) {
    fun getLocations(
            livedata: MutableLiveData<List<LocationModelAPI>>,
            requestState: MutableLiveData<RequestState>
    ) = retrofitService.getLocations(livedata, requestState)


    fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
        return localRepository.getUserData(email, password)
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
        localRepository.insertDataUser(
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
        return localRepository.checkTableUser()
    }

    fun checkTableLocation(): LiveData<LocationModelDB>? {
        return localRepository.checkTableLocation()
    }

    fun addLocationList(city: String, zip: String) {
        localRepository.insertDataLocation(city, zip)
    }

    fun addCarList(car: String) {
        localRepository.insertDataManafactura(car)
    }

    fun addTenderStatus(statusType: String) {
        localRepository.insertStatus(statusType)
    }

}