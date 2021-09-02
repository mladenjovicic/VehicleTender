
package com.mladenjovicic.vehicletender.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.TenderModelDB

@Database(entities = arrayOf(LocationModelDB::class), version = 1, exportSchema = false)
abstract class LocationDB:RoomDatabase() {
    abstract fun locationDAO():DAOAcess
    companion object{
        @Volatile
        private var INSTANCE:LocationDB?= null

        fun getDateLocation(context: Context):LocationDB{
            if(INSTANCE !=null)return INSTANCE!!
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context,LocationDB::class.java, "locations").fallbackToDestructiveMigration().build()
                return INSTANCE!!
            }
        }

    }

}
