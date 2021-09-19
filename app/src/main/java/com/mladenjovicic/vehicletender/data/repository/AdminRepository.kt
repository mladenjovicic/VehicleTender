package com.mladenjovicic.vehicletender.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.api.StatusModelAPI
import com.mladenjovicic.vehicletender.data.model.db.*
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository
import com.mladenjovicic.vehicletender.data.repository.db.dbRepository

class AdminRepository(private val retrofitService: RetrofitService,
                      private val localRepository: LocalRepository
) {
 fun addManufacturer(
     IdServer:Int,
     manufacturer_name:String
 ){
     localRepository.insertDataManafactura(IdServer, manufacturer_name)
 }

 fun getListManufacturer():LiveData<List<ManufacturerModelDB>>?{
     return localRepository.getDataManafactura()
  }

 fun addCarStock(year:Int,
                    modedLineId:Int,
                    mileage:Double,
                    price:Double,
                    comments:String,
                    locationId:Int,
                    regNo:String,
                    isSold:Boolean  ){
        localRepository.insertStockInfo( year,modedLineId,mileage,price,comments, locationId, regNo, isSold )
 }
    fun getListLocation(): LiveData<List<LocationModelDB>>? {
        return localRepository.getListLocation()!!
    }
    fun getCarModelsID():LiveData<List<ManAndCarModel>>?{
        return localRepository.getCarsInfo()
    }

    fun addNewLocation(serverId:Int, newLocation:String, zipCode:String){
         localRepository.insertDataLocation(serverId,newLocation,zipCode)
        }
    fun addTender(createdDate:String,createdBy:String,tenderNo:String,openDate:String,closeDate:String,statusId:Int){
        localRepository.insertDataTender( createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
        }

    fun getListStatus (): LiveData<List<StatusModelDB>>? {
        return localRepository.getStatus()
    }
    fun addNewUser( uuid:String, contact_name:String, contact_surname:String,email:String,password:String, status_user:Int,id_location:String,phone:String, company_name:String){
        localRepository.insertDataUser(uuid,contact_name, contact_surname, email, password, status_user, id_location, phone, company_name)
    }

    fun getListStockInfo():LiveData<List<StockInfoModelDB>>?{
        return localRepository.getCarStock()
    }
    fun getStockCarList():LiveData<List<stockCarList>>?{
        return localRepository.getCarStockList()

    }
    fun getUsersList():LiveData<List<UserModelDB>>?{
        return localRepository.getAllUser()
    }

    fun getTenderList(statusId: Int): LiveData<List<TenderModelDB>>? {
        return localRepository.getTenderList(statusId)

    }
    fun addCarModel(IdServer:Int,model_name:String, model_no:String,manufacturer_id:Int  ){
       localRepository.insertDataCar(IdServer, model_name, model_no, manufacturer_id)
    }
    fun getManufacturerJSON(
        manufacturerModelAPI: ManufacturerModelAPI,
        livedata: MutableLiveData<ManufacturerModelAPI>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.addManufacturerJSON(manufacturerModelAPI,livedata, requestState)

    }



