package com.mladenjovicic.vehicletender.repository.db



import android.content.Context
import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.db.RoomDB
import com.mladenjovicic.vehicletender.model.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class dbRepository {
    companion object{
        var roomDB:RoomDB?= null
        var locationModelDB:LiveData<List<LocationModelDB>>?= null
        var userModelDB:LiveData<UserModelDB>?=null
        var manufacturerModelDB:LiveData<List<ManufacturerModelDB>>?=null
        var listUserModelDB:LiveData<List<UserModelDB>>?=null

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

        fun getUserDateID(context: Context, id:Int):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().getUserDateID(id)
            return userModelDB
        }

        fun updateUser(context: Context, userModelDB: UserModelDB){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                var update = roomDB!!.userDAO().updateUser( userModelDB)

            }

        }


        fun getAllUser(context: Context):LiveData<List<UserModelDB>>?{
            roomDB = initializeDB(context)
            listUserModelDB = roomDB!!.userDAO().getUsersList()
            return listUserModelDB
        }

        fun checkTableUser(context: Context):LiveData<UserModelDB>?{
            roomDB = initializeDB(context)
            userModelDB = roomDB!!.userDAO().checkTableUser()
            return userModelDB
        }

        fun insertDataManafactura(context: Context, manufacturer_name:String){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val ManufacturerInsert = ManufacturerModelDB(manufacturer_name)
                roomDB!!.manufacturerDAO().InsertManafactura(ManufacturerInsert)
            }
        }

        fun getDataManafactura(context: Context):LiveData<List<ManufacturerModelDB>>?{
            roomDB = initializeDB(context)
            manufacturerModelDB = roomDB!!.manufacturerDAO().getManufacturer()
            return manufacturerModelDB
        }

        fun insertDataCar(context: Context, model_name:String,model_no:String, manufacturer_id:Int){
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val carInsert = CarModelDB(model_name,model_no, manufacturer_id)
                roomDB!!.carModelDAO().InsertCarModels(carInsert)
            }

        }
    }


}

