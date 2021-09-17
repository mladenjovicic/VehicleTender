package com.mladenjovicic.vehicletender.data.repository.db



import android.content.Context
import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.db.RoomDB
import com.mladenjovicic.vehicletender.data.model.db.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class dbRepository {
    companion object{
        var roomDB:RoomDB?= null
        var locationModelDB:LiveData<List<LocationModelDB>>?= null
        var checkLocationModelDB:LiveData<LocationModelDB>?= null
        var userModelDB:LiveData<UserModelDB>?=null
        var manufacturerModelDB:LiveData<List<ManufacturerModelDB>>?=null
        var listUserModelDB:LiveData<List<UserModelDB>>?=null
        var carModelDB:LiveData<List<CarModelDB>>?= null
        var ManAndCarModel:LiveData<List<ManAndCarModel>>?=null
        var StockInfoModelDB:LiveData<List<StockInfoModelDB>>?=null
        var stockCarList:LiveData<List<stockCarList>>?=null
        var status:LiveData<List<StatusModelDB>>?= null
        var tenderModelDBList:LiveData<List<TenderModelDB>>?= null
        var tenderModelDB:LiveData<TenderModelDB>?= null


        fun initializeDB(context: Context):RoomDB{
            return  RoomDB.getDateLocation(context)
        }

        fun insertDataLocation(context: Context,id:Int,city:String, zipCode:String ){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val LocationInsert = LocationModelDB(id, city,zipCode)
                roomDB!!.locationDAO().insertLocation(LocationInsert)
            }
        }

        fun getListLocation(context: Context):LiveData<List<LocationModelDB>>?{
            roomDB = initializeDB(context)
            locationModelDB = roomDB!!.locationDAO().getListLocation()
            return locationModelDB
        }
        fun checkTableLocation(context: Context):LiveData<LocationModelDB>?{
            roomDB = initializeDB(context)
            checkLocationModelDB = roomDB!!.locationDAO().checkTableLocation()
            return checkLocationModelDB
        }

        fun insertDataUser(context: Context, uudi:String,contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val UserInsert = UserModelDB(uudi, contact_name, contact_surname, email,password,status_user,id_location,phone,company_name)
                roomDB!!.userDAO().InsertUser(UserInsert)
            }
        }

        fun getUserDate(context: Context,email: String, password: String):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().getUser(email,password)
            return userModelDB
        }

        fun getUserDateID(context: Context, uuid: String):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().getUserDateID(uuid)
            return userModelDB
        }

        fun updateUser(context: Context, userModelDB: UserModelDB){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                var update = roomDB!!.userDAO().updateUser(userModelDB)

            }

        }

        fun getAllUser(context: Context):LiveData<List<UserModelDB>>?{
            roomDB = initializeDB(context)
            listUserModelDB = roomDB!!.userDAO().getUsersList()
            return listUserModelDB
        }

        fun checkTableUser(context: Context):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().checkTableUser()
            return userModelDB
        }

        fun insertDataManafactura(context: Context,id:Int, manufacturer_name:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val ManufacturerInsert = ManufacturerModelDB(id, manufacturer_name)
                roomDB!!.manufacturerDAO().InsertManafactura(ManufacturerInsert)
            }
        }

        fun getDataManafactura(context: Context):LiveData<List<ManufacturerModelDB>>?{
            roomDB = initializeDB(context)
            manufacturerModelDB = roomDB!!.manufacturerDAO().getManufacturer()
            return manufacturerModelDB
        }

        fun insertDataCar(context: Context, id:Int, model_name:String,model_no:String, manufacturer_id:Int){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val carInsert = CarModelDB(id, model_name,model_no, manufacturer_id)
                roomDB!!.carModelDAO().InsertCarModels(carInsert)
            }

        }

        fun getCarsInfo(context: Context):LiveData<List<ManAndCarModel>>?{
            roomDB = initializeDB(context)
            ManAndCarModel = roomDB!!.carModelDAO().getCarsInfo()
            return  ManAndCarModel
        }
        fun getCarModelId(context: Context, manufacturer_id:Int):LiveData<List<CarModelDB>>?{
            roomDB = initializeDB(context)
            carModelDB = roomDB!!.carModelDAO().getAllModelCarID(manufacturer_id)
            return carModelDB
        }

        fun insertDataBid(context: Context, userId: String, stockId:Int,price:Double,isWinningPrice:Boolean){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val bidInsert = BidModelDB(userId, stockId,price,isWinningPrice)
                roomDB!!.bidDAO().InsertBid(bidInsert)
            }
        }

        fun insertStatus(context: Context, id:Int, statusType:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val statusInsert = StatusModelDB(id, statusType)
                roomDB!!.statusDAO().InsertStatus(statusInsert)
            }
        }
        fun getStatus(context: Context):LiveData<List<StatusModelDB>>?{
            roomDB = initializeDB(context)
            status = roomDB!!.statusDAO().getStatus()
            return status

        }

        fun insertStockInfo(context:Context, year:Int, modelLineId:Int, mileage:Double,price:Double, comments:String, locationId:Int, regNo:String, isSold:Boolean){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val stockInfoInsert = StockInfoModelDB(year, modelLineId,mileage,price,comments,locationId,regNo, isSold)
                roomDB!!.stockInfoDAO().InsertStockInfo(stockInfoInsert)
            }

        }

        fun getCarStock(context: Context):LiveData<List<StockInfoModelDB>>?{
            roomDB = initializeDB(context)
            StockInfoModelDB = roomDB!!.stockInfoDAO().getStockInfo()
            return StockInfoModelDB
        }

        fun getCarStockList(context: Context):LiveData<List<stockCarList>>?{
            roomDB = initializeDB(context)
            stockCarList = roomDB!!.stockInfoDAO().getStockInfoList()
            return stockCarList
        }
        fun getCarStockListActive(context: Context, isSold: Boolean):LiveData<List<stockCarList>>?{
            roomDB = initializeDB(context)
            stockCarList = roomDB!!.stockInfoDAO().getStockCarActivesList(isSold)
            return stockCarList
        }

        fun insertDataTender(context: Context, createdDate:String, createdBy:String, tenderNo:String, openDate:String, closeDate:String, statusId:Int){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val tenderInsert = TenderModelDB(createdDate,createdBy,tenderNo,openDate,closeDate,statusId)
                roomDB!!.tenderDAO().InsertTender(tenderInsert)
            }
        }

        fun getTenderList(context: Context, statusId:Int):LiveData<List<TenderModelDB>>?{
            roomDB = initializeDB(context)
            tenderModelDBList = roomDB!!.tenderDAO().getTenderByStatus(statusId)
            return tenderModelDBList
        }

        fun getTenderNo(context: Context, tenderNo: String):LiveData<TenderModelDB>?{
            roomDB = initializeDB(context)
            tenderModelDB= roomDB!!.tenderDAO().getTenderByNo(tenderNo)
            return tenderModelDB

        }

        fun insertTenderStock(context: Context, stockId:Int,tenderId:String, saleDate:String){
            roomDB= initializeDB(context)
            CoroutineScope(IO).launch {
                val tenderStockInsert = TenderStockModelDB(stockId, tenderId, saleDate)
                roomDB!!.tenderStockDAO().InsertTenderStock(tenderStockInsert)
            }
        }

        fun insertTenderUser(context: Context,tenderId:Int,userId:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val tenderUserInsert = TenderUserModelDB(tenderId, userId)
                roomDB!!.tenderUserDAO().InsertTenderUser(tenderUserInsert)
            }
        }
    }


}

