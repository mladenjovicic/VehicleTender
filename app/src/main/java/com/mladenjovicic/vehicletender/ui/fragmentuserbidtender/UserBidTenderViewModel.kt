package com.mladenjovicic.vehicletender.ui.fragmentuserbidtender

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.repository.UserBidRepository

class UserBidTenderViewModel(private val userBidRepository :  UserBidRepository) : ViewModel() {
    var tenderStockModelDB:LiveData<List<TenderStockModelDB>>?= null
    var tenderFullListID:LiveData<List<TenderFullListID>>?=null
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        tenderStockModelDB = userBidRepository.getTenderStock()
        return tenderStockModelDB

    }

    fun getTenderFullListID(tenderId:String):LiveData<List<TenderFullListID>>?{
        tenderFullListID = userBidRepository.getTenderFullList(tenderId)
        return tenderFullListID
    }
}