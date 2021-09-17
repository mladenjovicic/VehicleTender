package com.mladenjovicic.vehicletender.ui.tender

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import com.mladenjovicic.vehicletender.data.repository.TenderUseRepositroy
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class TenderUseViewModel(private val tenderUseRepositroy: TenderUseRepositroy):ViewModel() {
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