package com.mladenjovicic.vehicletender.repository.db



import android.content.Context
import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.db.RoomDB
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class dbRepository {
    companion object{
        var roomDB:RoomDB?= null
        //var userDB:UserDB?=null
        var locationModelDB:LiveData<List<LocationModelDB>>?= null
        var userModelDB:LiveData<UserModelDB>?=null

        fun initializeDB(context: Context):RoomDB{
            return  RoomDB.getDateLocation(context)
        }


        fun insertDataLocation(context: Context,city:String, zipCode:String ){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val LocationInsert = LocationModelDB(city,zipCode)
                roomDB!!.locationDAO().insertLocation(LocationInsert)
            }
        }

        fun getListLocation(context: Context):LiveData<List<LocationModelDB>>?{
            roomDB = initializeDB(context)
            locationModelDB = roomDB!!.locationDAO().getListLocation()
            return locationModelDB
        }

        fun insertDataUser(context: Context, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val UserInsert = UserModelDB(contact_name, contact_surname, email,password,status_user,id_location,phone,company_name)
                roomDB!!.userDAO().InsertUser(UserInsert)
            }
        }

        fun getUserDate(context: Context,email: String, password: String):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().getUser(email,password)
            return userModelDB
        }
    }


}

