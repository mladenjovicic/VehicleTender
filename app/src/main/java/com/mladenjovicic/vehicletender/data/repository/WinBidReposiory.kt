package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class WinBidReposiory(
    private val retrofitService: RetrofitService,
    private val localRepository: LocalRepository
) {

    fun getBidListStockId(stockId:Int):LiveData<List<BidModelDB>>?{
        return localRepository.getBid(stockId)
    }
}