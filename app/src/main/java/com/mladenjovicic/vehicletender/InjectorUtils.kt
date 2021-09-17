package com.mladenjovicic.vehicletender

import android.content.Context
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInstanceN
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import com.mladenjovicic.vehicletender.data.repository.TenderUseRepositroy
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel

object  InjectorUtils {
    fun getContext(): Context = VTApplication.instance.applicationContext

    private val retrofitInstance by lazy { RetrofitInstanceN() }

    private val retrofitService by lazy { RetrofitService(retrofitInstance) }

    private fun getDatabaseService() = DatabaseService.getInstance(getContext())

    private val localRepository by lazy { LocalRepository(getDatabaseService()) }

    private val loginRepository by lazy { LoginRepository(retrofitService, localRepository) }

    fun getLoginViewModel() = LoginViewModel(loginRepository)

    private val tenderUseRepository by lazy { TenderUseRepositroy(retrofitService, localRepository)}

    fun getTenderUseViewModel() = TenderUseViewModel(tenderUseRepository)
}