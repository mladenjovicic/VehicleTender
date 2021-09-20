package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class TenderUseRepositroy (private val retrofitService: RetrofitService,
                           private val localRepository: LocalRepository
){

    fun getTenderNo(tenderNo: String): LiveData<TenderModelDB>?{
        return localRepository.getTenderNo(tenderNo)
    }
    fun getCarStockListActive(isSold: Boolean):LiveData<List<stockCarList>>?{

        return localRepository.getCarStockListActive(isSold)
    }
}