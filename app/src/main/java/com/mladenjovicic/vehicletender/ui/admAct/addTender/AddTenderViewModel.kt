package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import com.mladenjovicic.vehicletender.data.model.db.StatusModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import retrofit2.Response

class AddTenderViewModel(private val addTenderRepository: AdminRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewTender :MutableLiveData<TenderModelAPI?>

    init {
        createNewTender = MutableLiveData()
    }
    fun getNewTenderObserver():MutableLiveData<TenderModelAPI?>{
        return createNewTender
    }

    fun addTender(id:Int, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        addTenderRepository.addTender( id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }

    fun getListStatus (): LiveData<List<StatusModelDB>>? {
        return addTenderRepository.getListStatus()
    }

    fun addTenderJSON(id:Int?, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        createNewTender.postValue(null)
        addTenderRepository.addTenderJSON(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId,createNewTender,requestState )
    }

}