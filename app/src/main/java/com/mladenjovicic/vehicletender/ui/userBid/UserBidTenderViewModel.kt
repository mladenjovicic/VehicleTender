package com.mladenjovicic.vehicletender.ui.userBid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.model.db.TenderUserModelDB
import com.mladenjovicic.vehicletender.data.repository.UserBidRepository

class UserBidTenderViewModel(private val userBidRepository :  UserBidRepository) : ViewModel() {
    var tenderStockModelListDB:LiveData<List<TenderStockModelDB>>?= null
    var tenderFullListID:LiveData<List<TenderFullListID>>?=null
    var tenderUser:LiveData<TenderUserModelDB>?=null
    var tenderModelDB:LiveData<TenderModelDB>?=null
    lateinit var createNewBid: MutableLiveData<BidModelAPI?>
    lateinit var updateTender:MutableLiveData<TenderModelAPI?>
    val requestState = MutableLiveData<RequestState>()
    init {
        createNewBid = MutableLiveData()
        updateTender = MutableLiveData()
    }
    fun getNewBidObserver():MutableLiveData<BidModelAPI?>{
        return createNewBid
    }
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        tenderStockModelListDB = userBidRepository.getTenderStock()
        return tenderStockModelListDB

    }

    fun getTenderModelId(serverId: Int):LiveData<TenderModelDB>?{
        tenderModelDB = userBidRepository.getTenderModelId(serverId)
        return tenderModelDB
    }

    fun updateTenderObserver():MutableLiveData<TenderModelAPI?>{
        return updateTender
    }
    fun updateTenderDB(
        id:Int,
        createdDate: String,
        createdBy: String,
        tenderNo: String,
        openDate: String,
        closeDate: String,
        statusId: Int
    ){
        userBidRepository.updateTenderDB(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }

    fun getTenderFullListID(tenderId:String):LiveData<List<TenderFullListID>>?{
        tenderFullListID = userBidRepository.getTenderFullList(tenderId)
        return tenderFullListID
    }

    fun insertBid(serverId:Int, userId:String, stockId:Int, price:Double,isWinningPrice:Boolean, isActive:Boolean){
        userBidRepository.insertBid(serverId, userId, stockId, price, isWinningPrice, isActive)
    }

    fun deleteBid(
        userID:Int, stockId: Int, isActive:Boolean
    ){
        userBidRepository.deleteBid(userID, stockId, isActive)
    }

    fun inserBidJSON(token:String, serverId:Int?, userId:Int, stockId:Int, price:Double,isWinningPrice:Boolean?, isActive:Boolean?){
        createNewBid.postValue(null)
        userBidRepository.addBidJON(token, serverId,userId,stockId,price,isWinningPrice,isActive, createNewBid, requestState)
    }

    fun readTenderUser(uuid:String, serverId: Int ):LiveData<TenderUserModelDB>?{
        tenderUser = userBidRepository.readTenderUser(uuid, serverId)
        return  tenderUser

    }

    fun updateTenderJSON(
        token: String,
        id: Int?,
        createdDate:String,
        createdBy:String,
        tenderNo:String,
        openDate:String,
        closeDate:String,
        statusId:Int
    ){
        userBidRepository.updateTenderJSON(token,id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId, updateTender, requestState)
    }
}