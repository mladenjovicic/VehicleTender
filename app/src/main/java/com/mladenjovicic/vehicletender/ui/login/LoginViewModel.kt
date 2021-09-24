package com.mladenjovicic.vehicletender.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import kotlin.math.log

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
        var userModelDB:LiveData<UserModelDB>?=null
        var locationModelDB:LiveData<LocationModelDB>?= null

        val locationsLiveData = MutableLiveData<List<LocationModelAPI>>()
        val statusLiveData = MutableLiveData<List<StatusModelAPI>>()
        val manufacturerLiveData = MutableLiveData<List<ManufacturerModelAPI>>()
        val carModelLiveData= MutableLiveData<List<CarModelApi>>()
        val tenderUserLiveData = MutableLiveData<List<TenderUserModelAPI>>()
        val tenderStockLiveData = MutableLiveData<List<TenderStockModelAPI>>()
        val bidLiveData = MutableLiveData<List<BidModelAPI>>()
        val tenderLiveData = MutableLiveData<List<TenderModelAPI>>()
        val carStockLiveData = MutableLiveData<List<StockInfoModelAPI>>()
        val requestState = MutableLiveData<RequestState>()
        lateinit var  getTokenAPI: MutableLiveData<GetTokenAPI?>
        init {
            getLocationsJSON()
            getStatusJSON()
            getCarModelsJSON()
            getManufacturerJSON()

            getTenderUserJSON()
            getTenderStockJSON()
            getBidJSON()
            getTenderJSON()
            getCarStockJSON()
            getTokenAPI = MutableLiveData()
        }

         fun getTokenObserver():MutableLiveData<GetTokenAPI?>{
             return getTokenAPI
        }

         fun getToken(
            username:String,
            password:String
        ){
            loginRepository.getToken(username, password, getTokenAPI, requestState)
        }
        private fun getCarStockJSON(){
            loginRepository.getCarStockJSON(carStockLiveData, requestState)
        }
        private fun getTenderJSON(){
            loginRepository.getTenderJSON(tenderLiveData,requestState)

        }
        private fun getBidJSON(){
            loginRepository.getBidJSON(bidLiveData, requestState)
        }
        private fun getTenderStockJSON(){
            loginRepository.getTenderStockJSON(tenderStockLiveData, requestState)
        }

        private fun getTenderUserJSON(){
            loginRepository.getTenderUserJSON(tenderUserLiveData,requestState)
        }

        private fun getLocationsJSON() {
        loginRepository.getLocationsJSON(locationsLiveData, requestState)
        }

        private fun getStatusJSON(){
            loginRepository.getStatusJSON(statusLiveData, requestState)
        }

        private fun getCarModelsJSON(){
            loginRepository.getCarsModelJSON(carModelLiveData, requestState)
        }
        private fun getManufacturerJSON(){
            loginRepository.getManufacturerJSON(manufacturerLiveData, requestState)
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

        fun addTenderUser(serverID: Int, tenderId: String, userId: String){
            loginRepository.addTenderUser(serverID, tenderId, userId)
        }
        fun addTenderStock(serverID: Int,stockId: Int, tenderId: String, saleDate: String){
            loginRepository.addTenderStock(serverID,stockId,tenderId,saleDate)
        }
        fun addBid(serverID:Int, userId: String, stockId: Int, price: Double, isWinningPrice: Boolean){
        loginRepository.addBid(serverID, userId, stockId, price, isWinningPrice)
        }
        fun addTender(
            id:Int,
            createdDate: String,
            createdBy: String,
            tenderNo: String,
            openDate: String,
            closeDate: String,
            statusId: Int){
            loginRepository.addTender(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
        }

        fun addCarStock(
            serverID: Int,
            year: Int,
            modelLineId: Int,
            mileage: Double,
            price: Double,
            comments: String,
            locationId: Int,
            regNo: String,
            isSold: Boolean){
            loginRepository.addCarStock(serverID,year, modelLineId, mileage, price, comments, locationId, regNo, isSold)
        }


        fun addTenderStatus(id: Int, statusType: String) {
        loginRepository.addTenderStatus(id, statusType)
        }

         fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
             userModelDB = loginRepository.checkUser(email, password)
             return userModelDB
        }

}