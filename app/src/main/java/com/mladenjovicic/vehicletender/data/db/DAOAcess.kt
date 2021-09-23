package com.mladenjovicic.vehicletender.data.db


import androidx.lifecycle.LiveData
import androidx.room.*
import com.mladenjovicic.vehicletender.data.model.db.*

@Dao
interface DAOAcessLocation {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertLocation(locationModelDB: LocationModelDB)

    @Query("select * from location order by idServer asc")
    fun getListLocation():LiveData<List<LocationModelDB>>

    @Query("select * from location ORDER BY id LIMIT 1")
    fun checkTableLocation():LiveData<LocationModelDB>

}

@Dao
interface  DAOAcessUser{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(userModelDB: UserModelDB)

    @Query("select * from user where email = :email and password = :password")
    fun getUser(email:String, password: String):LiveData<UserModelDB>

    @Query("select * from user ORDER BY id LIMIT 1")
    fun checkTableUser():LiveData<UserModelDB>

    @Query("select * from user")
    fun getUsersList():LiveData<List<UserModelDB>>

    @Query("select * from user where uuid =:uuid")
    fun getUserDateID(uuid:String):LiveData<UserModelDB>

    @Query("update  user set company_name = :company_name where id = :id")
    fun updateUserID(company_name:String, id:Int )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(userModelDB: UserModelDB)
}

@Dao
interface DAOAcessTender{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTender(tenderModelDB: TenderModelDB)

    @Query("select * from tender")
    fun getTender():LiveData<List<TenderModelDB>>

    @Query("select * from tender where statusId = :statusId")
    fun getTenderByStatus(statusId:Int):LiveData<List<TenderModelDB>>

    @Query ("select* from tender where tenderNo = :tenderNo" )
    fun getTenderByNo(tenderNo:String):LiveData<TenderModelDB>
}

@Dao
interface DAOAcessManafactura{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertManafactura(manafacturaModelDB: ManufacturerModelDB)

    @Query("select *  from manufacturer")
    fun getManufacturer():LiveData<List<ManufacturerModelDB>>
}

@Dao
interface DAOAcessCarModels{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCarModels(carModelDB: CarModelDB)

    @Query ("select * from car_models")
    fun getCarModels():LiveData<List<CarModelDB>>

    @Query("select * from car_models where manufacturer_id =:manufacturer_id")
    fun getAllModelCarID(manufacturer_id:Int):LiveData<List<CarModelDB>>

    @Query("select car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from car_models left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer order by car_models.IdServer asc")
    fun getCarsInfo():LiveData<List<ManAndCarModel>>
}

@Dao
interface DAOAcessStockInfo{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStockInfo(stockInfoModelDB: StockInfoModelDB)

    @Query("select * from stockInfo")
    fun getStockInfo():LiveData<List<StockInfoModelDB>>

    @Query("select stockInfo.id, stockInfo.serverId,  stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from stockInfo left join location on stockInfo.locationId = location.id left join car_models on stockInfo.modelLineId = car_models.id left join manufacturer on car_models.manufacturer_id  = manufacturer.id order by stockInfo.id desc")
    fun getStockInfoList():LiveData<List<stockCarList>>

    @Query("select stockInfo.id, stockInfo.serverId, stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from stockInfo left join location on stockInfo.locationId = location.id left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer where isSold =:isSold order by stockInfo.id desc")
    fun getStockCarActivesList(isSold:Boolean):LiveData<List<stockCarList>>

    @Query("select stockInfo.id, stockInfo.serverId,  stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name, tenderStock.tenderId  from stockInfo left join location on stockInfo.locationId = location.id left join car_models on stockInfo.modelLineId = car_models.id left join manufacturer on car_models.manufacturer_id  = manufacturer.id left join tenderStock on stockInfo.serverId = tenderStock.stockId where isSold =:isSold order by stockInfo.id desc")
    fun getStockListCarUpdate(isSold: Boolean):LiveData<List<stockCarUpdate>>
}

@Dao
interface DAOAcessStatus{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStatus(statusModelDB: StatusModelDB)

    @Query("select*from status order by idServer asc")
    fun getStatus():LiveData<List<StatusModelDB>>
}

@Dao
interface DAOAcessBid{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  InsertBid(bidModelDB: BidModelDB)

    @Query("select * from bid")
    fun getBid(): LiveData<List<BidModelDB>>


}

@Dao
interface  DAOAcessTenderStock{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTenderStock(tenderStockModelDB: TenderStockModelDB)

    @Query("select * from tenderStock")
    fun getTenderStock():LiveData<List<TenderStockModelDB>>

    @Query("select * from tenderStock where stockId =:stockId and tenderId = :tenderId")
    fun getTenderStockId(stockId:Int, tenderId:String):LiveData<TenderStockModelDB>

    @Query("delete from tenderStock where stockId =:stockId and tenderId = :tenderId")
    fun deleteTenderStock(stockId:Int, tenderId:String)

    @Query("select tenderStock.id, tenderStock.stockId, tenderStock.tenderId, tenderStock.saleDate,stockInfo.id as stockInfoId, stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, location.zipCOde, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name  from tenderStock left join stockInfo on tenderStock.stockId =  stockInfo.serverId left join location on stockInfo.locationId = location.idServer left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id = manufacturer.IdServer where tenderId = :tenderId")
    fun getTenderID(tenderId:String):LiveData<List<TenderFullListID>>


}

@Dao
interface DAOAcessTenderUser{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTenderUser(tenderUserModelDB: TenderUserModelDB)

    @Query("select* from tenderUser")
    fun getTenderUser():LiveData<List<TenderUserModelDB>>
}
