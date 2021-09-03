
package com.mladenjovicic.vehicletender.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB

@Database(entities = arrayOf(UserModelDB::class, LocationModelDB::class), version = 4, exportSchema = false)
abstract class RoomDB:RoomDatabase() {
    abstract fun locationDAO():DAOAcessLocation
    abstract fun userDAO():DAOAcessUser

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
