package com.mladenjovicic.vehicletender

import android.content.Context
import androidx.lifecycle.ReportFragment
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInstanceN
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.db.DatabaseService
import com.mladenjovicic.vehicletender.data.repository.*
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository
import com.mladenjovicic.vehicletender.ui.admAct.ListCarStock.ListCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCarStock.AddCarStockViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationFragment
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addTender.AddTenderViewModel
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserViewModel
import com.mladenjovicic.vehicletender.ui.admAct.listTender.ListTenderViewModel
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.history.HistoryFragment
import com.mladenjovicic.vehicletender.ui.mainAct.history.SecViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.main.MainFragment
import com.mladenjovicic.vehicletender.ui.mainAct.main.MainViewModel
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsFragment
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsViewModel
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel
import com.mladenjovicic.vehicletender.ui.updateUser.UserUpdateViewModel

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

    fun getHistoryViewModel() = SecViewModel(mainRepository)

    fun getMainViewModel() =  MainViewModel(mainRepository)

    fun getReportsViewModel() = ReportsViewModel(mainRepository)
}