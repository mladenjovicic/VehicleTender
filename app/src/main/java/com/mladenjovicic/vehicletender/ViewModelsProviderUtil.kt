package com.mladenjovicic.vehicletender

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.ui.login.LoginViewModelTemp

object  ViewModelsProviderUtil {

    fun getLoginViewModel(activity: FragmentActivity) =
            ViewModelProvider(activity, LoginViewModelFactory()).get(LoginViewModelTemp::class.java)

    class LoginViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getLoginViewModel() as T }
    }
}