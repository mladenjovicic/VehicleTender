package com.mladenjovicic.vehicletender.ui.admAct.addCarStock

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.model.db.ManAndCarModel
import com.mladenjovicic.vehicletender.data.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AddCarStockViewModel(private val AddCarStockRepositror: AdminRepository) : ViewModel() {

    fun addCarStock(year:Int,modedLineId:Int,mileage:Double, price:Double,comments:String,locationId:Int,regNo:String,isSold:Boolean  ){
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
}