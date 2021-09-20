package com.mladenjovicic.vehicletender.ui.admAct.listTender

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository


class ListTenderViewModel(private val listTenedRepository: AdminRepository) : ViewModel() {

    var tenderModel:LiveData<List<TenderModelDB>>?= null
    fun getActivesTenderList(statusId:Int): LiveData<List<TenderModelDB>>?{
        tenderModel = listTenedRepository.getTenderList(statusId)
        return tenderModel
    }
}