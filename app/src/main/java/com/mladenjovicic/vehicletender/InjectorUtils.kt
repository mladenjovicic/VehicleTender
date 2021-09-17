package com.mladenjovicic.vehicletender

import android.content.Context
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitService

object  InjectorUtils {
    fun getContext(): Context = VTApplication.instance.applicationContext

    private val retrofitInstance by lazy { RetrofitInstance() }

    private val retrofitService by lazy { RetrofitService(retrofitInstance) }

    /*private fun getDatabaseService() = DatabaseService.getInstance(getContext())

    private val localRepository by lazy { LocalRepositoryTemp(getDatabaseService()) }

    private val loginRepository by lazy { LoginRepository(retrofitService, localRepository) }

    fun getLoginViewModel() = LoginViewModelTemp(loginRepository)*/
}