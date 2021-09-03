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
}