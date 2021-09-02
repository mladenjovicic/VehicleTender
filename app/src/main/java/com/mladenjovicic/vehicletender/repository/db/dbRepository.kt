package com.mladenjovicic.vehicletender.repository.db

import android.content.Context
import com.mladenjovicic.vehicletender.db.LocationDB
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class dbRepository {
    companion object{
        var localtionDB:LocationDB?= null

        fun initializeDB(context: Context):LocationDB{
            return  LocationDB.getDateLocation(context)
        }

        fun insertDataLocation(context: Context,city:String, zipCode:String ){
            localtionDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val LocationInsert = LocationModelDB(city,zipCode)
                localtionDB!!.locationDAO().insertLocation(LocationInsert)
            }
        }
    }


}