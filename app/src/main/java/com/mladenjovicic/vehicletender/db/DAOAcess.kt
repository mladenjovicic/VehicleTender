package com.mladenjovicic.vehicletender.db


import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mladenjovicic.vehicletender.model.db.*

@Dao
interface DAOAcessLocation {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertLocation(locationModelDB: LocationModelDB)

    @Query("select * from location")
    fun getListLocation():LiveData<List<LocationModelDB>>

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

    @Query("select * from user where id =:id")
    fun getUserDateID(id:Int):LiveData<UserModelDB>

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

}

@Dao
interface DAOAcessStockInfo{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStockInfo(stockInfoModelDB: StockInfoModelDB)

    @Query("select * from stockInfo")
    fun getStockInfo():LiveData<List<StockInfoModelDB>>
}

@Dao
interface DAOAcessStatus{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStatus(statusModelDB: StatusModelDB)

    @Query("select*from status")
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
}

@Dao
interface DAOAcessTenderUser{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTenderUser(tenderUserModelDB: TenderUserModelDB)

    @Query("select* from tenderUser")
    fun getTenderUser():LiveData<List<TenderUserModelDB>>
}
