package com.mladenjovicic.vehicletender.ui.admAct.addCarStock

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.model.db.CarModelDB
import com.mladenjovicic.vehicletender.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.model.db.ManAndCarModel
import com.mladenjovicic.vehicletender.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.repository.db.dbRepository
import java.util.*

class AddCarStockViewModel : ViewModel() {

    fun addCarStock(context: Context,year:Int,modedLineId:Int,mileage:Double, price:Double,comments:String,locationId:Int,regNo:String,isSold:Boolean  ){
        dbRepository.insertStockInfo(context, year,modedLineId,mileage,price,comments, locationId, regNo, isSold )
    }

    fun getListLocation (context: Context): LiveData<List<LocationModelDB>>? {
        return dbRepository.getListLocation(context)!!
    }

    fun getListManufacturer(context: Context):LiveData<List<ManufacturerModelDB>>?{
        return dbRepository.getDataManafactura(context)!!
    }

    fun getCarModelsID(context: Context):LiveData<List<ManAndCarModel>>?{
        return dbRepository.getCarsInfo(context)
    }
}