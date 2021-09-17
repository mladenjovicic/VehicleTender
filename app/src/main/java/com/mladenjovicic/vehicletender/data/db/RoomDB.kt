
package com.mladenjovicic.vehicletender.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.data.model.db.*

@Database(entities = arrayOf(UserModelDB::class, LocationModelDB::class, ManufacturerModelDB::class, CarModelDB::class, BidModelDB::class, StatusModelDB::class,
        StockInfoModelDB::class, TenderModelDB::class, TenderStockModelDB::class, TenderUserModelDB::class), version = 39, exportSchema = false)
abstract class RoomDB:RoomDatabase() {

    abstract fun locationDAO():DAOAcessLocation
    abstract fun userDAO():DAOAcessUser
    abstract fun manufacturerDAO():DAOAcessManafactura
    abstract fun carModelDAO():DAOAcessCarModels
    abstract fun bidDAO():DAOAcessBid
    abstract fun statusDAO():DAOAcessStatus
    abstract fun stockInfoDAO():DAOAcessStockInfo
    abstract fun tenderDAO():DAOAcessTender
    abstract fun tenderStockDAO():DAOAcessTenderStock
    abstract fun tenderUserDAO():DAOAcessTenderUser



    companion object{
        @Volatile
        private var INSTANCE:RoomDB?= null

        fun getDateLocation(context: Context):RoomDB{
            if(INSTANCE !=null)return INSTANCE!!
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context,RoomDB::class.java, "db").fallbackToDestructiveMigration().allowMainThreadQueries().build()
                return INSTANCE!!
            }
        }
    }
}
