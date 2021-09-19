package com.mladenjovicic.vehicletender.ui.admAct.ListUser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import com.mladenjovicic.vehicletender.data.repository.UserUpdateRespository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository


//class ListUserViewModel(private val adminRepository: AdminRepository) : ViewModel() {

    class ListUserViewModel(private val userUpdateRepositror: AdminRepository) : ViewModel() {
    var listAllUser:LiveData<List<UserModelDB>>?=null

    fun getUsersList(context: Context):LiveData<List<UserModelDB>>?{
        listAllUser = userUpdateRepositror.getUsersList()
        return listAllUser
    }
}