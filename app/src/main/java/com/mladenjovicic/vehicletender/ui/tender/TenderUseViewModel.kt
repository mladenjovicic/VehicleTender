package com.mladenjovicic.vehicletender.ui.tender

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.repository.TenderUseRepositroy

class TenderUseViewModel(private val tenderUseRepositroy: TenderUseRepositroy):ViewModel() {
    var tenderModelDB:LiveData<TenderModelDB>?=null
    var stockCarList:LiveData<List<stockCarList>>?=null

    fun getTenderBYTenderNo(tenderNo:String):LiveData<TenderModelDB>?{
        tenderModelDB = tenderUseRepositroy.getTenderNo(tenderNo)
        return tenderModelDB

    }

    fun getStockCarList(isSold:Boolean):LiveData<List<stockCarList>>?{
        stockCarList = tenderUseRepositroy.getCarStockListActive(isSold)
        return stockCarList
    }
}