package com.mladenjovicic.vehicletender.ui.admAct.listTender

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.model.db.stockCarList
import com.mladenjovicic.vehicletender.repository.db.dbRepository



class ListTenderViewModel : ViewModel() {

    var tenderModel:LiveData<List<TenderModelDB>>?= null
    fun getActivesTenderList(context: Context, statusId:Int): LiveData<List<TenderModelDB>>?{
        tenderModel = dbRepository.getTenderList(context,statusId)
        return tenderModel
    }
}