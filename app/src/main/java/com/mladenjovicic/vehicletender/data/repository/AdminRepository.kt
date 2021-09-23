package com.mladenjovicic.vehicletender.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.*
import com.mladenjovicic.vehicletender.data.model.db.*
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

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

 fun addCarStock(
     serverId: Int,
     year:Int,
                    modedLineId:Int,
                    mileage:Double,
                    price:Double,
                    comments:String,
                    locationId:Int,
                    regNo:String,
                    isSold:Boolean  ){
        localRepository.insertStockInfo( serverId, year,modedLineId,mileage,price,comments, locationId, regNo, isSold )
 }
    fun getListLocation(): LiveData<List<LocationModelDB>>? {
        return localRepository.getListLocation()!!
    }
    fun getCarModelsID():LiveData<List<ManAndCarModel>>?{
        return localRepository.getCarsInfo()
    }

    fun addNewLocation(
        serverId:Int,
        newLocation:String,
        zipCode:String){
         localRepository.insertDataLocation(serverId,newLocation,zipCode)
        }
    fun addTender(
        id:Int,
        createdDate:String,
        createdBy:String,
        tenderNo:String,
        openDate:String,
        closeDate:String,
        statusId:Int){
        localRepository.insertDataTender( id, createdDate, createdBy, tenderNo, openDate, closeDate, statusId)
        }

    fun getListStatus (): LiveData<List<StatusModelDB>>? {
        return localRepository.getStatus()
    }
    fun addNewUser(
        uuid:String,
        contact_name:String,
        contact_surname:String,
        email:String,password:String,
        status_user:Int,
        id_location:String,
        phone:String,
        company_name:String){
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
    fun addCarModel(
        IdServer:Int,
        model_name:String,
        model_no:String,
        manufacturer_id:Int){
       localRepository.insertDataCar(IdServer, model_name, model_no, manufacturer_id)
    }
    fun addManufacturerJSON(
        id:Int?,
        ManufacturerName:String,
        livedata: MutableLiveData<ManufacturerModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.addManufacturerJSON(id, ManufacturerName,livedata, requestState)

    fun addLocationJSON(
        id:Int?,
        city:String,
        zipCode: String,
        requestState: MutableLiveData<RequestState>,
        livedata: MutableLiveData<LocationModelAPI?>)= retrofitService.addLocationJSON(id, city, zipCode, requestState, livedata)

    fun addCarModelJSON(
        IdServer:Int?,
        model_name:String,
        model_no:String,
        manufacturer_id:Int,
        livedata: MutableLiveData<CarModelApi?>,
        requestState: MutableLiveData<RequestState>)=
        retrofitService.addCarModelJSON(IdServer, model_name, model_no, manufacturer_id,livedata, requestState)

    fun addTenderJSON(
        id: Int?,
        createdDate:String,
        createdBy:String,
        tenderNo:String,
        openDate:String,
        closeDate:String,
        statusId:Int,
        liveData: MutableLiveData<TenderModelAPI?>,
        requestState: MutableLiveData<RequestState>) = retrofitService.addTenderJSON(id, createdDate,createdBy, tenderNo, openDate, closeDate, statusId, liveData,requestState)

    fun addCarStockJSON(
        id: Int?,
        year:Int,
        modelLineId:Int,
        mileage:Double,
        price:Double,
        comments:String,
        locationId:Int,
        regNo:String,
        isSold:Boolean,
        liveData: MutableLiveData<StockInfoModelAPI?>,
        requestState: MutableLiveData<RequestState>)= retrofitService.addCarStockJSON(id, year, modelLineId, mileage, price, comments, locationId, regNo, isSold, liveData, requestState)
    }



