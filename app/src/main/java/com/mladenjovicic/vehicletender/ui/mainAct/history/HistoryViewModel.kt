package com.mladenjovicic.vehicletender.ui.mainAct.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.repository.MainRepository

class SecViewModel(private val historyRepositror: MainRepository)  : ViewModel() {

    var tenderModel: LiveData<List<TenderModelDB>>?= null

    fun getActivesTenderList(statusId:Int): LiveData<List<TenderModelDB>>?{
        tenderModel = historyRepositror.getTenderList(statusId)
        return tenderModel
    }
}