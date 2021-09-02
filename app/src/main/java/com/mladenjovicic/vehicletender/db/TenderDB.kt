package com.mladenjovicic.vehicletender.db
/*

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mladenjovicic.vehicletender.model.db.TenderModelDB

@Database(entities = arrayOf(TenderModelDB::class), version = 1, exportSchema = false)
abstract class TenderDB:RoomDatabase() {
    abstract fun tenderDAO():DAOAcess
    companion object{
        @Volatile
        private var INSTANCE:TenderDB?= null
         fun getDateTender(context: Context):TenderDB{
             if(INSTANCE!=null)return INSTANCE!!
             synchronized(this){
                 INSTANCE = Room.databaseBuilder(context,TenderDB::class.java, "tenders").fallbackToDestructiveMigration().build()
                 return INSTANCE!!
             }
         }


    }
}*/
