package com.mladenjovicic.vehicletender.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}

@Dao
interface DAOAcessTender{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTender(tenderModelDB:TenderModelDB)

    @Query (" select * from tenderModel")
    suspend fun getTender()
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