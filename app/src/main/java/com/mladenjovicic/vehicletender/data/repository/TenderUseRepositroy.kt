package com.mladenjovicic.vehicletender.data.repository

import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class TenderUseRepositroy (private val retrofitService: RetrofitService,
                           private val localRepository: LocalRepository
){
}