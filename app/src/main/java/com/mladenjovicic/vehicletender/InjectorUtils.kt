package com.mladenjovicic.vehicletender

import android.content.Context
import com.mladenjovicic.vehicletender.data.API.RetrofitInstanceN
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.repository.*
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository
import com.mladenjovicic.vehicletender.ui.ShowWinUsre.ShowWinUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.ListCarStock.ListCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCarStock.AddCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addTender.AddTenderViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.listTender.ListTenderViewModel
import com.mladenjovicic.vehicletender.ui.userBid.UserBidTenderViewModel
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.history.SecViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.main.UserHomeViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.mainAct.MainActivityViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsViewModel
import com.mladenjovicic.vehicletender.ui.tender.AddTenderUser.AddTenderUserViewModel
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.AddTenderStockViewModel
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.TenderUseViewModel
import com.mladenjovicic.vehicletender.ui.updateUser.UserUpdateViewModel
import com.mladenjovicic.vehicletender.ui.winBid.WinBidViewModel

object  InjectorUtils {
    fun getContext(): Context = VTApplication.instance.applicationContext

    private val retrofitInstance by lazy { RetrofitInstanceN() }

    private val retrofitService by lazy { RetrofitService(retrofitInstance) }

    private fun getDatabaseService() = DatabaseService.getInstance(getContext())

    private val localRepository by lazy { LocalRepository(getDatabaseService()) }

    private val loginRepository by lazy { LoginRepository(retrofitService, localRepository) }

    fun getLoginViewModel() = LoginViewModel(loginRepository)

    private val userBidRepositroy by lazy {UserBidRepository(retrofitService, localRepository)}
    fun getUserBidViewModel() = UserBidTenderViewModel(userBidRepositroy)

    private val tenderUseRepository by lazy { TenderUseRepositroy(retrofitService, localRepository)}

    fun getTenderUseViewModel() = TenderUseViewModel(tenderUseRepository)

    fun getAddTenderStockViewModel() = AddTenderStockViewModel(tenderUseRepository)

    fun getAddTenderUserViewModel() = AddTenderUserViewModel(tenderUseRepository)

    private val userUpdate by lazy { UserUpdateRespository(retrofitService, localRepository) }

    fun getUserUpdateViewModel() = UserUpdateViewModel(userUpdate)

    private val adminRepository by lazy { AdminRepository(retrofitService, localRepository)}

    fun getListUserUseViewModel() = ListUserViewModel(adminRepository)

    fun getListTenderViewModel() = ListTenderViewModel(adminRepository)

    fun addTenderViewMOdel() = AddTenderViewModel(adminRepository)

    fun getListCarStock()= ListCarStockViewModel(adminRepository)

    fun addCarStock() = AddCarStockViewModel(adminRepository)

    fun addLocation() = AddLocationViewModel(adminRepository)

    fun AddManafacturaAndCar()= AddManafacturaAndCarViewModel(adminRepository)

    fun AddUser() = AddUserViewModel(adminRepository)

    private val mainRepository by lazy { MainRepository(retrofitService, localRepository)}

    fun getMainActivityViewModel() = MainActivityViewModel(mainRepository)

    fun getHistoryViewModel() = SecViewModel(mainRepository)

    fun getMainViewModel() =  UserHomeViewModel(mainRepository)

    fun getReportsViewModel() = ReportsViewModel(mainRepository)

    private val winBidRepository by lazy { WinBidReposiory(retrofitService, localRepository)}

    fun getWinBidViewModel() = WinBidViewModel(winBidRepository)

    private val showWinBidReposiory by lazy {  ShowWinUserRepository(retrofitService, localRepository)}

    fun showWinUserViewModel() = ShowWinUserViewModel(showWinBidReposiory)

}