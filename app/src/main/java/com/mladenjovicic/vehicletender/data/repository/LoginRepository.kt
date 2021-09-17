package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class LoginRepository(private val retrofitService: RetrofitService,
                      private val localRepository:LocalRepository) {

    fun getLocations(
        livedata: MutableLiveData<List<LocationModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.readLocaitonJSON(livedata, requestState)

    fun getCarsModel(
        livedata: MutableLiveData<List<CarModelApi>>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.readCarModelJSON(livedata, requestState)

    fun getManufacturer(
        livedata: MutableLiveData<List<ManufacturerModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )= retrofitService.readManufacturerJSON(livedata, requestState)

    fun getStatus(
                   livedata: MutableLiveData<List<StatusModelAPI>>,
                  requestState: MutableLiveData<RequestState>
    ) = retrofitService.readStatusJSON(livedata, requestState)

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

    fun addLocationList(id:Int, city: String, zip: String) {
        localRepository.insertDataLocation(id, city, zip)
    }

    fun addCarModelList(id:Int, modelName:String, modelNo:String,manufacturerId:Int ){
        localRepository.insertDataCar(id, modelName,modelNo,  manufacturerId)
    }

    fun addCarList(id:Int, car: String) {
        localRepository.insertDataManafactura(id, car)
    }

    fun addTenderStatus(id:Int, statusType: String) {
        localRepository.insertStatus(id, statusType)
    }

    fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
        return localRepository.getUserData(email, password)
    }

}