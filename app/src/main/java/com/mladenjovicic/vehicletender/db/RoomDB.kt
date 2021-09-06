
package com.mladenjovicic.vehicletender.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.model.db.*

@Database(entities = arrayOf(UserModelDB::class, LocationModelDB::class, ManufacturerModelDB::class, CarModelDB::class), version = 5, exportSchema = false)
abstract class RoomDB:RoomDatabase() {
    abstract fun locationDAO():DAOAcessLocation
    abstract fun userDAO():DAOAcessUser
    abstract fun manufacturerDAO():DAOAcessManafactura
    abstract fun carModelDAO():DAOAcessCarModels



    companion object{
        @Volatile
        private var INSTANCE:RoomDB?= null

        fun getDateLocation(context: Context):RoomDB{
            if(INSTANCE !=null)return INSTANCE!!
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context,RoomDB::class.java, "db").fallbackToDestructiveMigration().build()
                return INSTANCE!!
            }
        }
    }
}
