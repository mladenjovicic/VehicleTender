package com.mladenjovicic.vehicletender.ui.winBid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.BidModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StockInfoModelAPI
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.data.repository.UserBidRepository
import com.mladenjovicic.vehicletender.data.repository.WinBidReposiory

class WinBidViewModel(private val winBidReposiory: WinBidReposiory) : ViewModel() {
     var bidModelDB:LiveData<List<BidModelDB>>?= null
    val requestState = MutableLiveData<RequestState>()
    lateinit var updateBidWin: MutableLiveData<BidModelAPI?>
    lateinit var updateCarStock:MutableLiveData<StockInfoModelAPI?>

    init {
        updateBidWin = MutableLiveData()
        updateCarStock = MutableLiveData()
    }

     fun getBidListStockId(stockId:Int):LiveData<List<BidModelDB>>?{
         bidModelDB = winBidReposiory.getBidListStockId(stockId)
         return bidModelDB
     }

    fun updateWin(
        token:String,
        id: Int?,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean?,
        isActive:Boolean?
    ){
        winBidReposiory.updateWin(token, id, TUserId, TStockId, Price, IsWinningPrice, isActive, updateBidWin, requestState)
    }

    fun updateWinDB(
        id: Int,
        TUserId:Int,
        TStockId:Int,
        Price:Double,
        IsWinningPrice:Boolean,
        isActive:Boolean
    ){
        winBidReposiory.updateWinDB(id, TUserId, TStockId, Price, IsWinningPrice, isActive)
    }

    fun getUpdateBidObserver():MutableLiveData<BidModelAPI?>{
        return updateBidWin
    }

    fun getUpdateCarStockObserver():MutableLiveData<StockInfoModelAPI?>{
        return updateCarStock
    }

    fun updateCarStock(
        token: String,
        Id:Int,
        isSold:Boolean
        ){
        winBidReposiory.updateCarStock(token, Id, isSold, updateCarStock ,requestState)
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
        winBidReposiory.updateCarStockDB(serverId, year, modelLineId, mileage, price, comments, locationId, regNo, isSold)
    }


}