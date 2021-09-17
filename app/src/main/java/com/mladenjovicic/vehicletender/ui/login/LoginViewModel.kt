package com.mladenjovicic.vehicletender.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
        var userModelDB:LiveData<UserModelDB>?=null
        var locationModelDB:LiveData<LocationModelDB>?= null

        val locationsLiveData = MutableLiveData<List<LocationModelAPI>>()
        val statusLiveData = MutableLiveData<List<StatusModelAPI>>()
        val manufacturerLiveData = MutableLiveData<List<ManufacturerModelAPI>>()
        val carModelLiveData= MutableLiveData<List<CarModelApi>>()
        val requestState = MutableLiveData<RequestState>()
        init {
            getLocations()
            getStatus()
            getCarModels()
            getManufacturer()
        }

        private fun getLocations() {
        loginRepository.getLocations(locationsLiveData, requestState)
        }

        private fun getStatus(){
            loginRepository.getStatus(statusLiveData, requestState)
        }

        private fun getCarModels(){
            loginRepository.getCarsModel(carModelLiveData, requestState)
        }
        private fun getManufacturer(){
            loginRepository.getManufacturer(manufacturerLiveData, requestState)
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

        fun addLocationList(id:Int, city: String, zip: String) {
        loginRepository.addLocationList(id, city, zip)
        }

        fun addCarModelList(id:Int, modelName:String, modelNo:String,manufacturerId:Int ){
        loginRepository.addCarModelList(id, modelName, modelNo, manufacturerId)
        }

        fun addCarList(id:Int, car: String) {
        loginRepository.addCarList(id, car)
        }

        fun addTenderStatus(id: Int, statusType: String) {
        loginRepository.addTenderStatus(id, statusType)
        }

         fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
        return loginRepository.checkUser(email, password)
        }

}