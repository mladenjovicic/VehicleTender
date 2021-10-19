package com.mladenjovicic.vehicletender.ui.ShowWinUsre

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.tenderWiningList
import com.mladenjovicic.vehicletender.data.repository.ShowWinUserRepository
import com.mladenjovicic.vehicletender.data.repository.WinBidReposiory

class ShowWinUserViewModel(private val showWinUserRepository: ShowWinUserRepository) : ViewModel() {

    var tenderWiningList:LiveData<List<tenderWiningList>>?=null

    fun bidUserWin(isWinningPrice:Boolean, tenderID:Int):LiveData<List<tenderWiningList>>?{

        tenderWiningList = showWinUserRepository.bidUserWin(isWinningPrice, tenderID)
        return tenderWiningList
    }

}