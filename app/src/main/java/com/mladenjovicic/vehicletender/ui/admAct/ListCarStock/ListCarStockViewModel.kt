package com.mladenjovicic.vehicletender.ui.admAct.ListCarStock

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.StockInfoModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class ListCarStockViewModel : ViewModel() {

    var listCarStock:LiveData<List<StockInfoModelDB>>?=null
    var stockCarList:LiveData<List<stockCarList>>?=null
    fun getListCarStock(context: Context):LiveData<List<StockInfoModelDB>>?{
        listCarStock= dbRepository.getCarStock(context)
        return listCarStock
    }

    fun getStockCarList(context: Context):LiveData<List<stockCarList>>?{
        stockCarList = dbRepository.getCarStockList(context)
        return stockCarList
    }
}