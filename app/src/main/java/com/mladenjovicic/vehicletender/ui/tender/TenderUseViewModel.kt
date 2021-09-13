package com.mladenjovicic.vehicletender.ui.tender

import android.content.Context
import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.model.db.stockCarList
import com.mladenjovicic.vehicletender.repository.db.dbRepository

class TenderUseViewModel:ViewModel() {
    var tenderModelDB:LiveData<TenderModelDB>?=null
    var stockCarList:LiveData<List<stockCarList>>?=null

    fun getTenderBYTenderNo(context: Context, tenderNo:String):LiveData<TenderModelDB>?{
        tenderModelDB = dbRepository.getTenderNo(context, tenderNo)
        return tenderModelDB

    }

    fun getStockCarList(context: Context, isSold:Boolean):LiveData<List<stockCarList>>?{
        stockCarList = dbRepository.getCarStockListActive(context, isSold)
        return stockCarList
    }
}