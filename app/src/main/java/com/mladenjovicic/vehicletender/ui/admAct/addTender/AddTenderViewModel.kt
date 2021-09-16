package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.StatusModelDB
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AddTenderViewModel : ViewModel() {
    fun addTender(context: Context, createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        dbRepository.insertDataTender(context, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }

    fun getListStatus (context: Context): LiveData<List<StatusModelDB>>? {
        return dbRepository.getStatus(context)
    }
}