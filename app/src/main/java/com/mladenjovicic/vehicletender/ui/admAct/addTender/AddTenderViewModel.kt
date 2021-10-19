package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.StockInfoModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderUserModelAPI
import com.mladenjovicic.vehicletender.data.model.db.StatusModelDB
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import retrofit2.Response

class AddTenderViewModel(private val addTenderRepository: AdminRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewTender :MutableLiveData<TenderModelAPI?>
    lateinit var createNewTenderUser: MutableLiveData<TenderUserModelAPI?>
    var listAllUser:LiveData<List<UserModelDB>>?=null

    init {
        createNewTender = MutableLiveData()
        createNewTenderUser = MutableLiveData()
    }
    fun getNewTenderObserver():MutableLiveData<TenderModelAPI?>{
        return createNewTender
    }
    fun getNewTenderUserObserver():MutableLiveData<TenderUserModelAPI?>{
        return createNewTenderUser
    }

    fun addTender(id:Int, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        addTenderRepository.addTender( id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }

    fun getListStatus (): LiveData<List<StatusModelDB>>? {
        return addTenderRepository.getListStatus()
    }

    fun addTenderJSON(token:String, id:Int?, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        createNewTender.postValue(null)
        addTenderRepository.addTenderJSON(token, id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId,createNewTender,requestState )
    }

    fun insertUserTender(
            token: String,
            id: Int?,
            tenderId:Int,
            userId:String,
    ){
        addTenderRepository.insertTenderUser(token, id, tenderId, userId,createNewTenderUser,requestState)
    }

    fun getUsersList():LiveData<List<UserModelDB>>?{
        listAllUser = addTenderRepository.getUsersList()
        return listAllUser
    }

    fun addTenderUser(
            id:Int,
            tenderId: Int,
            userId: String
    ){
        addTenderRepository.addTenderUser(id, tenderId, userId)
    }

}