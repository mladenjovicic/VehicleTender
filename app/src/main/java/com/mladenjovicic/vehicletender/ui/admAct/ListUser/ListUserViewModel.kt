package com.mladenjovicic.vehicletender.ui.admAct.ListUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository


class ListUserViewModel : ViewModel() {
    var listAllUser:LiveData<List<UserModelDB>>?=null
    fun getUsersList(context: Context):LiveData<List<UserModelDB>>?{
        listAllUser = dbRepository.getAllUser(context)
        return listAllUser
    }
}