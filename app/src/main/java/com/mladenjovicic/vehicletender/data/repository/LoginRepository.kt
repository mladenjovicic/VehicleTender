package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.TokenDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class LoginRepository(private val retrofitService: RetrofitService,
                      private val localRepository:LocalRepository) {

    fun getLocationsJSON(
        token:String,
        livedata: MutableLiveData<List<LocationModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.readLocaitonJSON(token, livedata, requestState)

    fun getCarsModelJSON(
        token: String,
        livedata: MutableLiveData<List<CarModelApi>>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.readCarModelJSON(token,livedata, requestState)

    fun deleteTenderServerIDStock(serverId: Int){
        localRepository.deleteTenderServerIDStock(serverId)
    }

    fun getManufacturerJSON(
        token: String,
        livedata: MutableLiveData<List<ManufacturerModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )= retrofitService.readManufacturerJSON(token,livedata, requestState)

    fun getStatusJSON(
            token: String,
            livedata: MutableLiveData<List<StatusModelAPI>>,
            requestState: MutableLiveData<RequestState>
    ) = retrofitService.readStatusJSON(token, livedata, requestState)

    fun getTenderUserJSON(
        token: String,
        livedata: MutableLiveData<List<TenderUserModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )=retrofitService.readTenderUserJSON(token,livedata, requestState)

    fun getTenderStockJSON(
        token: String,
        livedata: MutableLiveData<List<TenderStockModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )= retrofitService.readTenderStockJSON(token,livedata, requestState)

    fun getBidJSON(
        token: String,
        livedata: MutableLiveData<List<BidModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )= retrofitService.readBidJSON(token, livedata, requestState)

    fun getTenderJSON(
        token: String,
        livedata: MutableLiveData<List<TenderModelAPI>>,
        requestState: MutableLiveData<RequestState>
    )= retrofitService.readTenderJSON(token,livedata, requestState)

    fun getCarStockJSON(
        token: String,
        livedata: MutableLiveData<List<StockInfoModelAPI>>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.readCarStockJSON(token ,livedata, requestState)

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

    fun addUserToken(
        access_token:String,
        token_type:String,
        expires_in:String,
        userName:String,
        issued:String,
        expires:String){

        localRepository.insertUserToken(
            access_token,
            token_type,
            expires_in,
            userName,
            issued,
            expires
        )
    }

    fun readUserToken():LiveData<TokenDB>{
        return localRepository.readUserToken()
    }

    fun readUserEmail(email: String): LiveData<UserModelDB>? {
        return localRepository.readUserEmail(email)
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

    fun addTenderUser(serverId:Int, tenderId: Int, userId: String){
        localRepository.insertTenderUser(serverId, tenderId, userId)
    }

    fun addTenderStock(serverId: Int, stockId: Int, tenderId: String, saleDate: String?, isDeleted:Boolean){
        localRepository.insertTenderStock(serverId, stockId,tenderId,saleDate, isDeleted)
    }

    fun addBid(serverId:Int, userId: String, stockId: Int, price: Double, isWinningPrice: Boolean, isActive:Boolean){
        localRepository.insertDataBid(serverId,userId, stockId, price, isWinningPrice, isActive)
    }
    fun deleteBid(
        userID:Int, stockId: Int, isActive:Boolean
    ){
        localRepository.deleteBid(userID, stockId, isActive)
    }

    fun addTender(
        id:Int,
        createdDate: String,
        createdBy: String,
        tenderNo: String,
        openDate: String,
        closeDate: String,
        statusId: Int){
        localRepository.insertDataTender(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }
    fun addCarStock(
        serverId: Int,
        year: Int,
        modelLineId: Int,
        mileage: Int,
        price: Double,
        comments: String,
        locationId: Int,
        regNo: String,
        isSold: Boolean){
        localRepository.insertStockInfo(serverId, year, modelLineId,mileage,price, comments, locationId, regNo, isSold)
    }


    fun checkUser(email: String, password: String): LiveData<UserModelDB>? {
        return localRepository.getUserData(email, password)
    }

    fun getToken(
        username:String,
        password:String,
        liveData: MutableLiveData<GetTokenAPI?>,
        requestState: MutableLiveData<RequestState>)=
        retrofitService.getToken( username, password,liveData,requestState)

    fun getUserList(
            Authorization:String,
            liveData: MutableLiveData<List<UserProfilAPI?>>,
            requestState: MutableLiveData<RequestState>) =
            retrofitService.getListUser(Authorization,liveData,requestState)

    fun getUserProfil(
            Authorization:String,
            userEmail:String,
            liveData: MutableLiveData<UserProfilAPI?>,
            requestState: MutableLiveData<RequestState>
    )= retrofitService.getUserProfil(Authorization,userEmail,liveData, requestState)

    fun getUserRols(
        Authorization:String,
        liveData: MutableLiveData<List<UserRoleAPI>>,
        requestState: MutableLiveData<RequestState>
        )=retrofitService.getUserRols(Authorization, liveData, requestState)

    fun insertUserRole(
        ServerId:String,
        RoleId:String
         )=localRepository.insertRols(ServerId, RoleId)


    fun autoLogin():LiveData<TokenDB>?{
        return localRepository.autoLogin()
    }
}