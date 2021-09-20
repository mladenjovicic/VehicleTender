package com.mladenjovicic.vehicletender.ui.admAct.addCarStock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StockInfoModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.ManAndCarModel
import com.mladenjovicic.vehicletender.data.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository

class AddCarStockViewModel(private val AddCarStockRepositror: AdminRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    lateinit var createNewCarStock: MutableLiveData<StockInfoModelAPI?>

    init {
        createNewCarStock = MutableLiveData()
    }
    fun getNewCarStockObserver():MutableLiveData<StockInfoModelAPI?>{
        return createNewCarStock
    }

    fun addCarStock(
        id:Int,
        year:Int,
        modedLineId:Int,
        mileage:Double,
        price:Double,
        comments:String,
        locationId:Int,
        regNo:String,
        isSold:Boolean){
        AddCarStockRepositror.addCarStock(year,modedLineId,mileage,price,comments, locationId, regNo, isSold )
    }

    fun getListLocation (): LiveData<List<LocationModelDB>>? {
        return AddCarStockRepositror.getListLocation()!!
    }

    fun getListManufacturer():LiveData<List<ManufacturerModelDB>>?{
        return AddCarStockRepositror.getListManufacturer()!!
    }

    fun getCarModelsID():LiveData<List<ManAndCarModel>>?{
        return AddCarStockRepositror.getCarModelsID()
    }
    fun addCarStockJSON(
        id: Int?,
        year:Int,
        modelLineId:Int,
        mileage:Double,
        price:Double,
        comments:String,
        locationId:Int,
        regNo:String,
        isSold:Boolean,){
        AddCarStockRepositror.addCarStockJSON(id, year, modelLineId, mileage, price, comments, locationId, regNo, isSold,createNewCarStock, requestState)
    }
}