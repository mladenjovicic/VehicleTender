package com.mladenjovicic.vehicletender.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB

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
}

@Dao
interface DAOAcessTender{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTender(tenderModelDB:TenderModelDB)

    @Query (" select * from tenderModel")
    suspend fun getTender()
}