package com.mladenjovicic.vehicletender.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB

@Database(entities = arrayOf(UserModelDB::class, LocationModelDB::class), version = 1, exportSchema = false)
abstract class UserDB:RoomDatabase() {
    abstract fun userDAO():DAOAcessUser
    companion object{
        @Volatile
        private var INSTANCE:UserDB?= null

        fun getDateUser(context: Context):UserDB{
            if(INSTANCE !=null)return INSTANCE!!
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context,UserDB::class.java, "locations").fallbackToDestructiveMigration().build()
                return INSTANCE!!
            }
        }
    }
}