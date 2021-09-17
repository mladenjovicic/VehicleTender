package com.mladenjovicic.vehicletender.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.data.model.db.*

@Database(
    entities = [UserModelDB::class, LocationModelDB::class, ManufacturerModelDB::class, CarModelDB::class, BidModelDB::class, StatusModelDB::class, StockInfoModelDB::class, TenderModelDB::class, TenderStockModelDB::class, TenderUserModelDB::class],
    version = 2,
    exportSchema = false
)
abstract class VTDatabase: RoomDatabase() {
    abstract fun locationDAO(): DAOAcessLocation
    abstract fun userDAO(): DAOAcessUser
    abstract fun manufacturerDAO(): DAOAcessManafactura
    abstract fun carModelDAO(): DAOAcessCarModels
    abstract fun bidDAO(): DAOAcessBid
    abstract fun statusDAO(): DAOAcessStatus
    abstract fun stockInfoDAO(): DAOAcessStockInfo
    abstract fun tenderDAO(): DAOAcessTender
    abstract fun tenderStockDAO(): DAOAcessTenderStock
    abstract fun tenderUserDAO(): DAOAcessTenderUser

}