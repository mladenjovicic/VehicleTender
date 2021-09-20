package com.mladenjovicic.vehicletender.ui.admAct.ListCarStock

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.StockInfoModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.repository.AdminRepository

class ListCarStockViewModel(private val ListCarStockRepositror: AdminRepository) : ViewModel() {

    var listCarStock:LiveData<List<StockInfoModelDB>>?=null
    var stockCarList:LiveData<List<stockCarList>>?=null


   @JvmName("getStockCarList1")
   fun getStockCarList():LiveData<List<stockCarList>>?{
        stockCarList = ListCarStockRepositror.getStockCarList()
        return stockCarList
    }
}