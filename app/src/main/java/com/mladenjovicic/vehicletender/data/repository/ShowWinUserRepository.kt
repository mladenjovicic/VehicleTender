package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.db.tenderWiningList
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class ShowWinUserRepository(
    private val retrofitService: RetrofitService,
    private val localRepository: LocalRepository
) {

    fun bidUserWin(isWinningPrice:Boolean, tenderId:Int ):LiveData<List<tenderWiningList>>?{
        println("deb1" + isWinningPrice)
        return  localRepository.bidUserWin(isWinningPrice, tenderId)

    }
}