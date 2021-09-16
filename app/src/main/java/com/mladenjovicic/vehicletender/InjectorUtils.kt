package com.mladenjovicic.vehicletender

import android.content.Context
import com.mladenjovicic.vehicletender.data.api.RetrofitInstance
import com.mladenjovicic.vehicletender.data.api.RetrofitService
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.repository.LoginRepository
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepositoryTemp
import com.mladenjovicic.vehicletender.ui.login.LoginViewModelTemp

object InjectorUtils {

    fun getContext(): Context = VTApplication.instance.applicationContext

    private val retrofitInstance by lazy { RetrofitInstance() }

    private val retrofitService by lazy { RetrofitService(retrofitInstance) }

    private fun getDatabaseService() = DatabaseService.getInstance(getContext())

    private val localRepository by lazy { LocalRepositoryTemp(getDatabaseService()) }

    private val loginRepository by lazy { LoginRepository(retrofitService, localRepository) }

    fun getLoginViewModel() = LoginViewModelTemp(loginRepository)

}