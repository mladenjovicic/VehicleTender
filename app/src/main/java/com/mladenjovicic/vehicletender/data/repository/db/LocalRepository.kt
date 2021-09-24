package com.mladenjovicic.vehicletender.data.repository.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.db.RoomDB
import com.mladenjovicic.vehicletender.data.db.VTDatabase
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

    fun insertDataLocation(id:Int ,city: String, zipCode: String) {
        CoroutineScope(IO).launch {
            val LocationInsert = LocationModelDB(id, city, zipCode)
            databaseService.locationDAO.insertLocation(LocationInsert)
        }
    }

    fun getListLocation(): LiveData<List<LocationModelDB>>? {
        locationModelDB = databaseService.locationDAO.getListLocation()
        return locationModelDB
    }

    fun checkTableLocation(): LiveData<LocationModelDB>? {
        checkLocationModelDB = databaseService.locationDAO.checkTableLocation()
        return checkLocationModelDB
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

    fun getUserData(email: String, password: String): LiveData<UserModelDB>? {
        userModelDB = databaseService.userDAO.getUser(email, password)
        return userModelDB
    }

    fun getUserDateID(uuid: String): LiveData<UserModelDB>? {
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

    fun checkTableUser(): LiveData<UserModelDB>? {
        userModelDB = databaseService.userDAO.checkTableUser()
        return userModelDB
    }

    fun insertDataManafactura(id:Int, manufacturer_name: String) {
        CoroutineScope(IO).launch {
            val ManufacturerInsert = ManufacturerModelDB(id, manufacturer_name)
            databaseService.manufacturerDAO.InsertManafactura(ManufacturerInsert)
        }
    }

    fun getDataManafactura(): LiveData<List<ManufacturerModelDB>>? {
        manufacturerModelDB = databaseService.manufacturerDAO.getManufacturer()
        return manufacturerModelDB
    }

    fun insertDataCar(id:Int, model_name: String, model_no: String, manufacturer_id: Int) {
        CoroutineScope(IO).launch {
            val carInsert = CarModelDB(id, model_name, model_no, manufacturer_id)
            databaseService.carModelDAO.InsertCarModels(carInsert)
        }

    }

    fun getCarsInfo(): LiveData<List<ManAndCarModel>>? {
        ManAndCarModel = databaseService.carModelDAO.getCarsInfo()
        return ManAndCarModel
    }

    fun getCarModelId(manufacturer_id: Int): LiveData<List<CarModelDB>>? {
        carModelDB = databaseService.carModelDAO.getAllModelCarID(manufacturer_id)
        return carModelDB
    }

    fun insertDataBid(serverId:Int, userId: String, stockId: Int, price: Double, isWinningPrice: Boolean) {
        CoroutineScope(IO).launch {
            val bidInsert = BidModelDB(serverId, userId, stockId, price, isWinningPrice)
            databaseService.bidDAO.InsertBid(bidInsert)
        }
    }

    fun getBid(stockId: Int):LiveData<List<BidModelDB>>?{
        bidModelDB = databaseService.bidDAO.getBid(stockId)
        return bidModelDB
    }

    fun insertStatus(id:Int, statusType: String) {
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
        mileage: Double,
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

    fun getCarStockListUpdate(isSold: Boolean):LiveData<List<stockCarUpdate>>?{
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

    fun getTenderList(statusId: Int): LiveData<List<TenderModelDB>>? {
        tenderModelDBList = databaseService.tenderDAO.getTenderByStatus(statusId)
        return tenderModelDBList
    }

    fun getTenderNo(tenderNo: String): LiveData<TenderModelDB>? {
        tenderModelDB = databaseService.tenderDAO.getTenderByNo(tenderNo)
        return tenderModelDB

    }

    fun insertTenderStock(serverId: Int, stockId: Int, tenderId: String, saleDate: String) {
        CoroutineScope(IO).launch {
            val tenderStockInsert = TenderStockModelDB(serverId, stockId, tenderId, saleDate)
            databaseService.tenderStockDAO.InsertTenderStock(tenderStockInsert)
        }
    }
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        tenderStockModelDB= databaseService.tenderStockDAO.getTenderStock()
        return tenderStockModelDB
    }

    fun insertTenderUser(serverId:Int, tenderId: String, userId: String) {
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
}
