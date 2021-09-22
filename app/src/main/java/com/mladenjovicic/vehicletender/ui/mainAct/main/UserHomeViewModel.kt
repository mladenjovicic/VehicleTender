package com.mladenjovicic.vehicletender.ui.mainAct.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.repository.MainRepository

class UserHomeViewModel(private val mainRepositror: MainRepository)  : ViewModel() {
    var tenderModel: LiveData<List<TenderModelDB>>?= null
    fun getActivesTenderList(statusId:Int): LiveData<List<TenderModelDB>>?{
        tenderModel = mainRepositror.getTenderList(statusId)
        return tenderModel
    }
}