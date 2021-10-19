package com.mladenjovicic.vehicletender.data.repository.db

import android.media.session.MediaSession
import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.model.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LocalRepository(private val databaseService: DatabaseService) {
    var locationModelDB: LiveData<List<LocationModelDB>>? = null
    var checkLocationModelDB: LiveData<LocationModelDB>? = null
    var userModelDB: LiveData<UserModelDB>? = null
    var manufacturerModelDB: LiveData<List<ManufacturerModelDB>>? = null
    var listUserModelDB: LiveData<List<UserModelDB>>? = null
    var carModelDB: LiveData<List<CarModelDB>>? = null
    var ManAndCarModel: LiveData<List<ManAndCarModel>>? = null
    var StockInfoModelDB: LiveData<List<StockInfoModelDB>>? = null
    var stockCarList: LiveData<List<stockCarList>>? = null
    var status: LiveData<List<StatusModelDB>>? = null
    var tenderModelDBList: LiveData<List<TenderModelDB>>? = null
    var tenderModelDB: LiveData<TenderModelDB>? = null
    var bidModelDB:LiveData<List<BidModelDB>>? = null
    var tenderStockModelDB:LiveData<List<TenderStockModelDB>>?= null
    var tenderFullListID:LiveData<List<TenderFullListID>>?=null
    var stockCarUpdate:LiveData<List<stockCarUpdate>>?= null
    var roleUser:LiveData<List<RoleDB>>?=null
    var tenderUserModelDB:LiveData<TenderUserModelDB>?= null
    var tenderWiningList:LiveData<List<tenderWiningList>>?=null
    var tokenDB:LiveData<TokenDB>?=null

    fun insertDataLocation(
        id:Int,
        city: String,
        zipCode: String){
        CoroutineScope(IO).launch {
            val LocationInsert = LocationModelDB(id, city, zipCode)
            databaseService.locationDAO.insertLocation(LocationInsert)
        }
    }

     fun deleteTenderStock(stockId:Int, tenderId:String){
        databaseService.tenderStockDAO.deleteTenderStock(stockId, tenderId)
    }
    fun deleteTenderServerIDStock(serverId: Int){
        databaseService.tenderStockDAO.deleteTenderServerIDStock(serverId)
    }

    fun getListLocation(): LiveData<List<LocationModelDB>>? {
        locationModelDB = databaseService.locationDAO.getListLocation()
        return locationModelDB
    }

    fun checkTableLocation(): LiveData<LocationModelDB>? {
        checkLocationModelDB = databaseService.locationDAO.checkTableLocation()
        return checkLocationModelDB
    }
    fun insertUserToken(
        access_token:String,
        token_type:String,
        expires_in:String,
        userName:String,
        issued:String,
        expires:String){
        CoroutineScope(IO).launch {
            val tokenDB = TokenDB(1,access_token, token_type, expires_in, userName, issued, expires)
            databaseService.tokenUserDAO.InsertUserToken(tokenDB)
        }
    }



    fun readUserToken():LiveData<TokenDB>{
        return databaseService.tokenUserDAO.readUserToken()
    }

    fun readUserTender():LiveData<List<TenderUserModelDB>>{
        return databaseService.tenderUserDAO.getTenderUser()
    }

    fun getUserList(statusId:String):LiveData<List<UserModelDB>>{
        return databaseService.userDAO.getUserList(statusId)
    }

    fun getUserListUser(statusId: String):LiveData<List<UserListUser>>{
        return databaseService.userDAO.getUserListUser(statusId)
    }
    fun getUserListStatusId(statusId: String):LiveData<List<UserListStatusId>>{
        return  databaseService.userDAO.getUserListStatusId(statusId)
    }
    fun insertDataUser(
        uudi: String,
        contact_name: String,
        contact_surname: String,
        email: String,
        password: String,
        status_user: Int,
        id_location: String,
        phone: String,
        company_name: String
    ) {
        CoroutineScope(IO).launch {
            val UserInsert = UserModelDB(
                uudi,
                contact_name,
                contact_surname,
                email,
                password,
                status_user,
                id_location,
                phone,
                company_name
            )
            databaseService.userDAO.InsertUser(UserInsert)
        }
    }

    fun getUserData(
        email: String,
        password: String): LiveData<UserModelDB>? {
        userModelDB = databaseService.userDAO.getUser(email, password)
        return userModelDB
    }

    fun getUserDateID(
        uuid: String): LiveData<UserModelDB>? {
        userModelDB = databaseService.userDAO.getUserDateID(uuid)
        return userModelDB
    }

    fun updateUser(userModelDB: UserModelDB) {

        CoroutineScope(IO).launch {
            var update = databaseService.userDAO.updateUser(userModelDB)
        }
    }

    fun getAllUser(): LiveData<List<UserModelDB>>? {
        listUserModelDB = databaseService.userDAO.getUsersList()
        return listUserModelDB
    }

    fun readUserEmail(email: String): LiveData<UserModelDB>? {
        userModelDB = databaseService.userDAO.readUserEmail(email)
        return userModelDB
    }

    fun insertDataManafactura(
        id:Int,
        manufacturer_name: String){
        CoroutineScope(IO).launch {
            val ManufacturerInsert = ManufacturerModelDB(id, manufacturer_name)
            databaseService.manufacturerDAO.InsertManafactura(ManufacturerInsert)
        }
    }

    fun getDataManafactura(): LiveData<List<ManufacturerModelDB>>? {
        manufacturerModelDB = databaseService.manufacturerDAO.getManufacturer()
        return manufacturerModelDB
    }

    fun insertDataCar(
        id:Int,
        model_name: String,
        model_no: String
        , manufacturer_id: Int) {
        CoroutineScope(IO).launch {
            val carInsert = CarModelDB(id, model_name, model_no, manufacturer_id)
            databaseService.carModelDAO.InsertCarModels(carInsert)
        }

    }

    fun getCarsInfo(): LiveData<List<ManAndCarModel>>? {
        ManAndCarModel = databaseService.carModelDAO.getCarsInfo()
        return ManAndCarModel
    }

    fun getCarModelId(
        manufacturer_id: Int
    ): LiveData<List<CarModelDB>>? {
        carModelDB = databaseService.carModelDAO.getAllModelCarID(manufacturer_id)
        return carModelDB
    }

    fun insertDataBid(
        serverId:Int,
        userId: String,
        stockId: Int,
        price: Double,
        isWinningPrice: Boolean,
        isActive:Boolean
        ) {
        CoroutineScope(IO).launch {
            val bidInsert = BidModelDB(serverId, userId, stockId, price, isWinningPrice, isActive)
            databaseService.bidDAO.InsertBid(bidInsert)
        }
    }



    fun getBid(
        stockId: Int
        ):LiveData<List<BidModelDB>>?{
        bidModelDB = databaseService.bidDAO.getBid(stockId)
        return bidModelDB
    }


    fun insertStatus(
        id:Int,
        statusType: String){
        CoroutineScope(IO).launch {
            val statusInsert = StatusModelDB(id, statusType)
            databaseService.statusDAO.InsertStatus(statusInsert)
        }
    }

    @JvmName("getStatus1")
    fun getStatus(): LiveData<List<StatusModelDB>>? {
        status = databaseService.statusDAO.getStatus()
        return status

    }

    fun insertStockInfo(
        serverId: Int,
        year: Int,
        modelLineId: Int,
        mileage: Int,
        price: Double,
        comments: String,
        locationId: Int,
        regNo: String,
        isSold: Boolean
    ) {
        CoroutineScope(IO).launch {
            val stockInfoInsert = StockInfoModelDB(
                serverId,
                year,
                modelLineId,
                mileage,
                price,
                comments,
                locationId,
                regNo,
                isSold
            )
            databaseService.stockInfoDAO.InsertStockInfo(stockInfoInsert)
        }

    }

    fun getCarStock(): LiveData<List<StockInfoModelDB>>? {

        StockInfoModelDB = databaseService.stockInfoDAO.getStockInfo()
        return StockInfoModelDB
    }

    fun getCarStockList(): LiveData<List<stockCarList>>? {
        stockCarList = databaseService.stockInfoDAO.getStockInfoList()
        return stockCarList
    }

    fun getCarStockListActive(isSold: Boolean): LiveData<List<stockCarList>>? {
        stockCarList = databaseService.stockInfoDAO.getStockCarActivesList(isSold)
        return stockCarList
    }

    fun getCarStockListUpdate(
        isSold: Boolean):LiveData<List<stockCarUpdate>>?{
        stockCarUpdate = databaseService.stockInfoDAO.getStockListCarUpdate(isSold)
        return stockCarUpdate
    }

    fun insertDataTender(
        id:Int,
        createdDate: String,
        createdBy: String,
        tenderNo: String,
        openDate: String,
        closeDate: String,
        statusId: Int
    ) {
        CoroutineScope(IO).launch {
            val tenderInsert =
                TenderModelDB(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
            databaseService.tenderDAO.InsertTender(tenderInsert)
        }
    }

    fun bidUserWin(isWinningPrice: Boolean, tenderId: Int):LiveData<List<tenderWiningList>>?{
        println("deb2" + isWinningPrice.toString())
        tenderWiningList = databaseService.bidDAO.bidUserWin(isWinningPrice, tenderId)
        return  tenderWiningList
    }

    fun getTenderList(statusId: Int): LiveData<List<TenderModelDB>>? {
        tenderModelDBList = databaseService.tenderDAO.getTenderByStatus(statusId)
        return tenderModelDBList
    }

    fun getTenderNo(tenderNo: String): LiveData<TenderModelDB>? {
        tenderModelDB = databaseService.tenderDAO.getTenderByNo(tenderNo)
        return tenderModelDB

    }

    fun insertTenderStock(serverId: Int, stockId: Int, tenderId: String?, saleDate: String?, isDeleted:Boolean) {
        CoroutineScope(IO).launch {
            val tenderStockInsert = TenderStockModelDB(serverId, stockId, tenderId, saleDate, isDeleted)
            databaseService.tenderStockDAO.InsertTenderStock(tenderStockInsert)
        }
    }
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        tenderStockModelDB= databaseService.tenderStockDAO.getTenderStock()
        return tenderStockModelDB
    }
    fun getTenderModelId(serverId: Int):LiveData<TenderModelDB>?{
        tenderModelDB = databaseService.tenderDAO.getTenderModelId(serverId)
        return tenderModelDB
    }

    fun insertTenderUser(serverId:Int, tenderId: Int, userId: String) {
        CoroutineScope(IO).launch {
            val tenderUserInsert = TenderUserModelDB(serverId, tenderId, userId)
            databaseService.tenderUserDAO.InsertTenderUser(tenderUserInsert)
        }
    }
    fun deleteTenderStock(id:Int, tenderId:String, saleDate:String){
        databaseService.tenderStockDAO.deleteTenderStock(id, tenderId)
    }



    fun getTenderFullListID(tenderId: String):LiveData<List<TenderFullListID>>?{
        tenderFullListID = databaseService.tenderStockDAO.getTenderID(tenderId)
        return tenderFullListID
    }

    fun insertRols(ServerId:String,RoleId:String ){
        CoroutineScope(IO).launch {
            val insertRole = RoleDB(ServerId, RoleId)
            databaseService.roleDAO.InsertRole(insertRole)
        }
    }

    fun readRole():LiveData<List<RoleDB>>?{
        roleUser = databaseService.roleDAO.readRole()
        return roleUser
    }

    fun readTenderUser(uuid: String, serverId: Int ):LiveData<TenderUserModelDB>?{
        tenderUserModelDB = databaseService.tenderUserDAO.readTenderUser(uuid, serverId)
        return tenderUserModelDB
    }

    fun deleteBid(
        userID:Int, stockId: Int, isActive:Boolean
    ){
        databaseService.bidDAO.deleteBid(userID,stockId)
    }

    fun deleteAll(){
        databaseService.bidDAO.deleteBidAll()
        databaseService.userDAO.deleteUserAll()
        databaseService.carModelDAO.deleteCarModelsAll()
        databaseService.locationDAO.deleteLocationALL()
        databaseService.manufacturerDAO.deleteManafacturaAll()
        databaseService.roleDAO.deleteRoleALL()
        databaseService.statusDAO.deleteStatusAll()
        databaseService.stockInfoDAO.deleteStockInfoAll()
        databaseService.tenderDAO.deleteTenderAll()
        databaseService.tenderStockDAO.deleteTenderStockALL()
        databaseService.tenderUserDAO.deleteTenderUserALL()
        databaseService.tokenUserDAO.deleteUserToken()
    }

    fun deleteUserToken(){
        databaseService.tokenUserDAO.deleteUserToken()
    }

    fun autoLogin():LiveData<TokenDB>?{
        tokenDB = databaseService.tokenUserDAO.readUserToken()
        return tokenDB
    }
}
