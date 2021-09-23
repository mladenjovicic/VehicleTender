package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
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

    fun insertBid(serverId:Int, userId:String, stockId:Int, price:Double,isWinningPrice:Boolean ){
        localRepository.insertDataBid(serverId, userId, stockId, price,isWinningPrice)
    }

    fun addBidJON(
        id: Int?,
        TUserId:String,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean,
        liveData: MutableLiveData<BidModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.addBidJSON(id, TUserId,TStockId, Price, IsWinningPrice, liveData, requestState)
}