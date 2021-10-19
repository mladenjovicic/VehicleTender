package com.mladenjovicic.vehicletender.ui.tender.addTenderStock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.TenderStockModelAPI
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.model.db.stockCarUpdate
import com.mladenjovicic.vehicletender.data.repository.TenderUseRepositroy

class AddTenderStockViewModel(private val tenderUseRepositroy: TenderUseRepositroy) : ViewModel() {

    var tenderModelDB: LiveData<TenderModelDB>?=null
    var stockCarList: LiveData<List<stockCarList>>?=null
    var stockCarUpdate: LiveData<List<stockCarUpdate>>?=null
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewTenderStock: MutableLiveData<TenderStockModelAPI?>
    init {
        createNewTenderStock = MutableLiveData()
    }

    fun getTenderBYTenderNo(tenderNo:String): LiveData<TenderModelDB>?{
        tenderModelDB = tenderUseRepositroy.getTenderNo(tenderNo)
        return tenderModelDB

    }

    fun getStockCarList(isSold:Boolean): LiveData<List<stockCarList>>?{
        stockCarList = tenderUseRepositroy.getCarStockListActive(isSold)
        return stockCarList
    }

    fun getStockCarUpdate(isSold: Boolean): LiveData<List<stockCarUpdate>>?{
        stockCarUpdate = tenderUseRepositroy.getCarStockUpdate(isSold)
        return stockCarUpdate    }

    fun insertTenderStock(serverId:Int, id:Int, tenderId:String?, saleDate:String, isDeleted:Boolean){
        tenderUseRepositroy.insertTenderStock(serverId, id, tenderId, saleDate, isDeleted)
    }
    fun deleteTenderStock(id:Int, tenderId: String, saleDate: String){
        tenderUseRepositroy.deleteTenderStock(id, tenderId, saleDate)
    }

    fun addTenderStock(
        token: String,
        id: Int?,
        stockId:Int,
        tenderId:Int,
        saleDate:String,
        isDeleted:Boolean
        ){
        tenderUseRepositroy.addTenderStock(token, id, stockId, tenderId,saleDate, isDeleted, createNewTenderStock, requestState)
    }

    fun updateTenderStockJSON(
            token: String,
            id: Int?,
            stockId:Int,
            tenderId:Int,
            saleDate:String,
            isDeleted:Boolean
    ){
        tenderUseRepositroy.updateTenderStockJSON(token,id,stockId, tenderId,saleDate, isDeleted, createNewTenderStock, requestState)
    }

    fun getNewLocationObserver(): MutableLiveData<TenderStockModelAPI?> {
        return createNewTenderStock
    }
}