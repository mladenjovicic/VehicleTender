package com.mladenjovicic.vehicletender.ui.tender.AddTenderUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.StockInfoModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderUserModelAPI
import com.mladenjovicic.vehicletender.data.model.db.*
import com.mladenjovicic.vehicletender.data.repository.TenderUseRepositroy

class AddTenderUserViewModel(private val tenderUseRepositroy: TenderUseRepositroy) : ViewModel() {
    var userUpdate: LiveData<List<UserListStatusId>>?=null
    var userListUser :LiveData<List<UserListUser>>?=null
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewTenderUser: MutableLiveData<TenderUserModelAPI?>

    init {
        createNewTenderUser = MutableLiveData()
    }

      fun readTenderUser():LiveData<List<TenderUserModelDB>>{
          return tenderUseRepositroy.readTenderUser()

      }

      fun readUser(statusId:String):LiveData<List<UserListStatusId>>?{
          userUpdate = tenderUseRepositroy.getUserListStatusId(statusId)
          return userUpdate
      }

      fun getUserListUser(statusId: String):LiveData<List<UserListUser>>?{
          userListUser =  tenderUseRepositroy.getUserListUser(statusId)
          return userListUser
      }

    fun insertUserTender(
        token: String,
        id: Int?,
        tenderId:Int,
        userId:String,
    ){
        tenderUseRepositroy.insertTenderUser(token, id, tenderId, userId,createNewTenderUser,requestState)
    }

    fun getNetUserTenderObserver():LiveData<TenderUserModelAPI?>{
        return createNewTenderUser
    }
    fun addTenderUser(
        id:Int,
        tenderId: Int,
        userId: String
    ){
        tenderUseRepositroy.addTenderUser(id, tenderId, userId)
    }


}