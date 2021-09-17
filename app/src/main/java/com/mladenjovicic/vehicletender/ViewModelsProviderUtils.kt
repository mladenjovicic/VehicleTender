package com.mladenjovicic.vehicletender

import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel

object ViewModelsProviderUtils {
    fun getLoginViewModel(activity: FragmentActivity) =
        ViewModelProvider(activity, LoginViewModelFactory()).get(LoginViewModel::class.java)

    class LoginViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getLoginViewModel() as T }
    }

    fun getTenderViewModel(activity: FragmentActivity)=
        ViewModelProvider(activity, TenderUseViewModelFactory()).get(TenderUseViewModel::class.java)

    class TenderUseViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getTenderUseViewModel() as T }
    }

}