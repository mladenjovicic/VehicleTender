package com.mladenjovicic.vehicletender.ui.updateUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.UserModelDB
import com.mladenjovicic.vehicletender.model.db.userModelDB1
import com.mladenjovicic.vehicletender.repository.db.dbRepository

class UserUpdateViewModel : ViewModel() {
    var userModelDB:LiveData<UserModelDB>?=null

    fun getUserDateID(context: Context, userId:Int): LiveData<UserModelDB>? {
        userModelDB =  dbRepository.getUserDateID(context, userId)
        return  userModelDB
    }

    fun getListLocation (context: Context): LiveData<List<LocationModelDB>>? {
        return dbRepository.getListLocation(context)!!
    }

    fun updateUser(context: Context){
        var userModelDB: UserModelDB?= null
        userModelDB = UserModelDB("rad","rad","r","2",0,"1","1","1")
        println("test11" + userModelDB)
        dbRepository.updateUser(context, userModelDB )
    }
}