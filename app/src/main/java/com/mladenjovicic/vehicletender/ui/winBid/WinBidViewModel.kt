package com.mladenjovicic.vehicletender.ui.winBid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.data.repository.UserBidRepository
import com.mladenjovicic.vehicletender.data.repository.WinBidReposiory

class WinBidViewModel(private val winBidReposiory: WinBidReposiory) : ViewModel() {
     var bidModelDB:LiveData<List<BidModelDB>>?= null

     fun getBidListStockId(stockId:Int):LiveData<List<BidModelDB>>?{
         bidModelDB = winBidReposiory.getBidListStockId(stockId)
         return bidModelDB
     }
}