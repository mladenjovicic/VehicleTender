package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.model.db.TenderUserModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class UserBidRepository(private val retrofitService: RetrofitService,
                        private val localRepository: LocalRepository
) {
    fun getTenderStock():LiveData<List<TenderStockModelDB>>?{
        return  localRepository.getTenderStock()
    }

    fun getTenderFullList(tenderID:String):LiveData<List<TenderFullListID>>?{
        return  localRepository.getTenderFullListID(tenderID)
    }

    fun insertBid(serverId:Int, userId:String, stockId:Int, price:Double,isWinningPrice:Boolean, isActive:Boolean ){
        localRepository.insertDataBid(serverId, userId, stockId, price,isWinningPrice, isActive)
    }

    fun deleteBid(
        userID:Int, stockId: Int, isActive:Boolean
    ){
        localRepository.deleteBid(userID, stockId, isActive)
    }

    fun addBidJON(
        token:String,
        id: Int?,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean?,
        isActive:Boolean?,
        liveData: MutableLiveData<BidModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.addBidJSON(token, id, TUserId,TStockId, Price, IsWinningPrice, isActive, liveData, requestState)

    fun readTenderUser(uuid:String, serverId: Int):LiveData<TenderUserModelDB>?{
         return localRepository.readTenderUser(uuid, serverId)
    }

    fun updateTenderJSON(
        token: String,
        id: Int?,
        createdDate:String,
        createdBy:String,
        tenderNo:String,
        openDate:String,
        closeDate:String,
        statusId:Int,
        liveData: MutableLiveData<TenderModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.updateTenderJSON(token, id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId, liveData, requestState)

    fun getTenderModelId(serverId: Int):LiveData<TenderModelDB>?{
        return localRepository.getTenderModelId(serverId)
    }
    fun  updateTenderDB(
        id:Int,
        createdDate: String,
        createdBy: String,
        tenderNo: String,
        openDate: String,
        closeDate: String,
        statusId: Int){
        localRepository.insertDataTender(id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
    }
}