package com.mladenjovicic.vehicletender.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.UserModelDB
import com.mladenjovicic.vehicletender.repository.db.dbRepository

class LoginViewModel : ViewModel() {
        var userModelDB:LiveData<UserModelDB>?=null

        fun checkUser(context: Context, email:String, password:String):LiveData<UserModelDB>?{
            userModelDB =  dbRepository.getUserDate(context, email, password)
            return userModelDB
        }

        fun addNewUser(context: Context,uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        dbRepository.insertDataUser(context,uuid, contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
        }

        fun checkTableUser(context: Context):LiveData<UserModelDB>?{
            userModelDB = dbRepository.checkTableUser(context)
            return userModelDB
        }
}