package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class MainRepository(private val retrofitService: RetrofitService,
                     private val localRepository: LocalRepository
) {

    fun getTenderList(statusId: Int): LiveData<List<TenderModelDB>>? {
        return localRepository.getTenderList(statusId)

    }
}