package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
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
}