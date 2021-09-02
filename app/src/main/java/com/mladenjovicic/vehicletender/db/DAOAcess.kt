package com.mladenjovicic.vehicletender.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB

@Dao
interface DAOAcess {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTender(tenderModelDB:TenderModelDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationModelDB: LocationModelDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(userModelDB: UserModelDB)

}