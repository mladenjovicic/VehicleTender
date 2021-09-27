package com.mladenjovicic.vehicletender.ui.userBid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.repository.UserBidRepository

class UserBidTenderViewModel(private val userBidRepository :  UserBidRepository) : ViewModel() {
    var tenderStockModelDB:LiveData<List<TenderStockModelDB>>?= null
    var tenderFullListID:LiveData<List<TenderFullListID>>?=null
    lateinit var createNewBid: MutableLiveData<BidModelAPI?>
    val requestState = MutableLiveData<RequestState>()
    init {
        createNewBid = MutableLiveData()
    }
    fun getNewBidObserver():MutableLiveData<BidModelAPI?>{
        return createNewBid
    }
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        tenderStockModelDB = userBidRepository.getTenderStock()
        return tenderStockModelDB

    }

    fun getTenderFullListID(tenderId:String):LiveData<List<TenderFullListID>>?{
        tenderFullListID = userBidRepository.getTenderFullList(tenderId)
        return tenderFullListID
    }

    fun insertBid(serverId:Int, userId:String, stockId:Int, price:Double,isWinningPrice:Boolean){
        userBidRepository.insertBid(serverId, userId, stockId, price, isWinningPrice)
    }

    fun inserBidJSON(token:String, serverId:Int?, userId:String, stockId:Int, price:Double,isWinningPrice:Boolean){
        createNewBid.postValue(null)
        userBidRepository.addBidJON(token, serverId,userId,stockId,price,isWinningPrice, createNewBid, requestState)
    }
}