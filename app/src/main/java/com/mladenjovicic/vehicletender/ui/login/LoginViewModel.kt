package com.mladenjovicic.vehicletender.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.TokenDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.LoginRepository

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
        val userRoleLiveData = MutableLiveData<List<UserRoleAPI>>()
        var tokenLiveData :LiveData<TokenDB>?=null
        val requestState = MutableLiveData<RequestState>()
        lateinit var  getTokenAPI: MutableLiveData<GetTokenAPI?>
        lateinit var getUserList: MutableLiveData<List<UserProfilAPI?>>
        lateinit var getUserProfil:MutableLiveData<UserProfilAPI?>
        lateinit var getRolelist:MutableLiveData<List<UserRoleAPI?>>
        init {
            getTokenAPI = MutableLiveData()
            getUserList = MutableLiveData()
            getUserProfil = MutableLiveData()
            getRolelist = MutableLiveData()
        }

        fun autoLogin():LiveData<TokenDB>?{
            tokenLiveData = loginRepository.autoLogin()
            return tokenLiveData
        }

         fun getTokenObserver():MutableLiveData<GetTokenAPI?>{
             return getTokenAPI
        }
        fun deleteTenderStock(serverId:Int){
        loginRepository.deleteTenderServerIDStock(serverId)
        }
        fun getUserListObserver():MutableLiveData<List<UserProfilAPI?>>{
            return getUserList
        }


         fun getToken(
            username:String,
            password:String
        ){
            loginRepository.getToken(username, password, getTokenAPI, requestState)
        }
        fun getUserProfil(token: String, userEmail:String){
        loginRepository.getUserProfil(token, userEmail, getUserProfil, requestState)
        }

        fun deleteBid(userID:Int, stockId: Int, isActive:Boolean){
            loginRepository.deleteBid(userID, stockId, isActive)
        }

        fun getUserRols(Authorization:String,
                        ){
            loginRepository.getUserRols(Authorization,userRoleLiveData, requestState)
        }

        fun getUserList(token:String){
        loginRepository.getUserList(token,getUserList, requestState)
        }
        fun getCarStockJSON(token:String){
            loginRepository.getCarStockJSON(token, carStockLiveData, requestState)
        }
         fun getTenderJSON(token:String){
            loginRepository.getTenderJSON(token, tenderLiveData,requestState)

        }
         fun getBidJSON(token:String){
            loginRepository.getBidJSON(token, bidLiveData, requestState)
        }
         fun getTenderStockJSON(token:String){
            loginRepository.getTenderStockJSON(token, tenderStockLiveData, requestState)
        }

         fun getTenderUserJSON(token:String){
            loginRepository.getTenderUserJSON(token, tenderUserLiveData,requestState)
        }

        fun getLocationsJSON(token:String) {
        loginRepository.getLocationsJSON(token, locationsLiveData, requestState)
        }

         fun getStatusJSON(token: String){
            loginRepository.getStatusJSON(token, statusLiveData, requestState)
        }

         fun getCarModelsJSON(token:String){
            loginRepository.getCarsModelJSON(token, carModelLiveData, requestState)
        }
         fun getManufacturerJSON(token:String){
            loginRepository.getManufacturerJSON(token, manufacturerLiveData, requestState)
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
    fun insertUserRole(
        serverId:String,
        RoleId:String){
        loginRepository.insertUserRole(serverId,RoleId)
    }

        fun readUserEmail(email: String): LiveData<UserModelDB>? {
        userModelDB = loginRepository.readUserEmail(email)
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

        fun addTenderUser(serverID: Int, tenderId: Int, userId: String){
            loginRepository.addTenderUser(serverID, tenderId, userId)
        }
        fun addTenderStock(serverID: Int,stockId: Int, tenderId: String, saleDate: String?, isDeleted:Boolean){
            loginRepository.addTenderStock(serverID,stockId,tenderId,saleDate, isDeleted)
        }
        fun addBid(serverID:Int, userId: String, stockId: Int, price: Double, isWinningPrice: Boolean, isActive:Boolean){
        loginRepository.addBid(serverID, userId, stockId, price, isWinningPrice, isActive)
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
            mileage: Int,
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

        fun addUserToken(
            access_token:String,
            token_type:String,
            expires_in:String,
            userName:String,
            issued:String,
            expires:String
        ){
            loginRepository.addUserToken(
                access_token,
                token_type,
                expires_in,
                userName,
                issued,
                expires)
        }

        fun readUserToken():LiveData<TokenDB>?{
            tokenLiveData = loginRepository.readUserToken()
            return  tokenLiveData
        }

}