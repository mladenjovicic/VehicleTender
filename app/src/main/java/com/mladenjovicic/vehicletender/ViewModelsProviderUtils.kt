package com.mladenjovicic.vehicletender

import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.adapter.BidWinAdapter
import com.mladenjovicic.vehicletender.ui.admAct.ListCarStock.ListCarStockFragment
import com.mladenjovicic.vehicletender.ui.admAct.ListCarStock.ListCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCarStock.AddCarStockFragment
import com.mladenjovicic.vehicletender.ui.admAct.addCarStock.AddCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarFragment
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationFragment
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addTender.AddTenderViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addTender.addTenderFragment
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.listTender.ListTenderFragment
import com.mladenjovicic.vehicletender.ui.admAct.listTender.ListTenderViewModel
import com.mladenjovicic.vehicletender.ui.userBid.UserBidTenderViewModel
import com.mladenjovicic.vehicletender.ui.userBid.fragmentUserBidTender
import com.mladenjovicic.vehicletender.ui.mainAct.history.HistoryFragment
import com.mladenjovicic.vehicletender.ui.mainAct.history.SecViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.main.UserHomeFragment
import com.mladenjovicic.vehicletender.ui.mainAct.main.UserHomeViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsFragment
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsViewModel
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel
import com.mladenjovicic.vehicletender.ui.updateUser.UserUpdateViewModel
import com.mladenjovicic.vehicletender.ui.winBid.WinBidFragment
import com.mladenjovicic.vehicletender.ui.winBid.WinBidViewModel

object ViewModelsProviderUtils {
    fun getLoginViewModel(activity: FragmentActivity) =
        ViewModelProvider(activity, LoginViewModelFactory()).get(LoginViewModel::class.java)

    class LoginViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getLoginViewModel() as T }
    }
    fun getUserBidViewModel(activity: fragmentUserBidTender) =
        ViewModelProvider(activity, UserBidViewModelFactory()).get(UserBidTenderViewModel::class.java)

    class UserBidViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getUserBidViewModel() as T }
    }

    fun getBidWinViewModel(activity: WinBidFragment) =
        ViewModelProvider(activity, WinBidViewModelFactory()).get(WinBidViewModel::class.java)

    class WinBidViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getWinBidViewModel() as T }
    }


    fun getTenderViewModel(activity: FragmentActivity)=
        ViewModelProvider(activity, TenderUseViewModelFactory()).get(TenderUseViewModel::class.java)

    class TenderUseViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getTenderUseViewModel() as T }
    }

    fun getUpdteUserViewMode(activity: FragmentActivity)=
        ViewModelProvider(activity, UpdateUserViewModelFactory()).get(UserUpdateViewModel::class.java)

    class UpdateUserViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getUserUpdateViewModel() as T }
    }

    fun getListUserViewModel(activity: ListUserFragment)=
        ViewModelProvider(activity, ListUserViewModelFactory()).get(ListUserViewModel::class.java)

    class ListUserViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getListUserUseViewModel() as T }
    }

    fun getListTender(activity:ListTenderFragment)=
        ViewModelProvider(activity, ListTenderViewModelFactory()).get(ListTenderViewModel::class.java)

    class ListTenderViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getListTenderViewModel() as T }
    }

    fun addTender(activity: addTenderFragment)=
        ViewModelProvider(activity, AddTenderViewModelFactory()).get(AddTenderViewModel::class.java)

    class AddTenderViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.addTenderViewMOdel() as T }
    }
    fun addCarStock(activity: AddCarStockFragment)=
        ViewModelProvider(activity, AddCarStockViewModelFactory()).get(AddCarStockViewModel::class.java)

    class AddCarStockViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.addCarStock() as T }
    }

    fun getListCarStock(activity: ListCarStockFragment)=
        ViewModelProvider(activity, ListCarStockViewModelFactory()).get(ListCarStockViewModel::class.java)

    class ListCarStockViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getListCarStock() as T }
    }
    fun addLocation(activity: AddLocationFragment)=
        ViewModelProvider(activity, addLocationViewModelFactory()).get(AddLocationViewModel::class.java)

    class addLocationViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.addLocation() as T }
    }
    fun AddManafacturaAndCar(activity: AddManafacturaAndCarFragment)=
        ViewModelProvider(activity, AddManafacturaAndCarViewModelFactory()).get(AddManafacturaAndCarViewModel::class.java)

    class AddManafacturaAndCarViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.AddManafacturaAndCar() as T }
    }

    fun AddNewUser(activity: AddUserFragment)=
        ViewModelProvider(activity, AddNewUserViewModelFactory()).get(AddUserViewModel::class.java)

    class AddNewUserViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.AddUser() as T }
    }

    fun reprotsUser(activity: ReportsFragment)=
        ViewModelProvider(activity, ReportsViewModelFactory()).get(ReportsViewModel::class.java)

    class ReportsViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getReportsViewModel() as T }
    }
    fun mainUser(activity: UserHomeFragment)=
        ViewModelProvider(activity, mainViewModelFactory()).get(UserHomeViewModel::class.java)

    class mainViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getMainViewModel() as T }
    }
    fun historyUser(activity: HistoryFragment)=
        ViewModelProvider(activity, HistoryViewModelFactory()).get(SecViewModel::class.java)

    class HistoryViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T { return InjectorUtils.getHistoryViewModel() as T }
    }


}