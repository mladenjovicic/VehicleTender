package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.StockInfoModelAPI
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class WinBidReposiory(
    private val retrofitService: RetrofitService,
    private val localRepository: LocalRepository
) {


    fun getBidListStockId(
        stockId:Int
        ):LiveData<List<BidModelDB>>?{
        return localRepository.getBid(stockId)
    }

    fun updateWin(
        token:String,
        id: Int?,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean?,
        isActive:Boolean?,
        liveData: MutableLiveData<BidModelAPI?>,
        requestState: MutableLiveData<RequestState>

    ){
        retrofitService.UpdateBidJSON(token, id, TUserId, TStockId, Price, IsWinningPrice, isActive, liveData, requestState)
    }

    fun updateWinDB(
        id: Int,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean,
        isActive:Boolean
    ){
        localRepository.insertDataBid(id,TUserId.toString(), TStockId, Price, IsWinningPrice, isActive)
    }

    fun updateCarStock(token: String,
                       Id:Int,
                       isSold:Boolean,
                       liveData: MutableLiveData<StockInfoModelAPI?>,
                       requestState: MutableLiveData<RequestState>){
        retrofitService.updateCarStockJSON(token, Id, isSold, liveData, requestState)
    }

    fun updateCarStockDB(
        serverId: Int,
        year: Int,
        modelLineId: Int,
        mileage: Int,
        price: Double,
        comments: String,
        locationId: Int,
        regNo: String,
        isSold: Boolean
    ){
        localRepository.insertStockInfo(serverId,year, modelLineId, mileage, price, comments, locationId, regNo, isSold)
    }
}