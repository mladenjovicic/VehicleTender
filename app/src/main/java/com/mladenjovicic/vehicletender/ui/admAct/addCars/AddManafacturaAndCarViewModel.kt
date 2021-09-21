package com.mladenjovicic.vehicletender.ui.admAct.addCars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI
import com.mladenjovicic.vehicletender.data.model.db.ManufacturerModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import retrofit2.Response

class AddManafacturaAndCarViewModel(private val addManafacturaAndCarRepository: AdminRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    val manufacturerLiveData = MutableLiveData<ManufacturerModelAPI>()
    lateinit var createNewManufacturer: MutableLiveData<ManufacturerModelAPI?>
    lateinit var createNewCarModele: MutableLiveData<CarModelApi?>


    init {
        createNewManufacturer = MutableLiveData()
        createNewCarModele = MutableLiveData()
    }

    fun getNewManufacturerObserver(): MutableLiveData<ManufacturerModelAPI?> {
        return createNewManufacturer
    }

    fun getNewCarModelObserver(): MutableLiveData<CarModelApi?> {
        return createNewCarModele
    }

    fun addManufacturer(id:Int,manufacturer_name:String){
            addManafacturaAndCarRepository.addManufacturer(id,manufacturer_name)
     }
    fun addManufacturerJSON(id:Int?,ManufacturerName:String){
        createNewManufacturer.postValue(null)
        addManafacturaAndCarRepository.addManufacturerJSON(id, ManufacturerName,  createNewManufacturer, requestState)
    }

    fun getListManafactura():LiveData<List<ManufacturerModelDB>>{
        return addManafacturaAndCarRepository.getListManufacturer()!!
    }

    fun addCarModel(id:Int, model_name:String, model_no:String, manufacturer_id:Int ){
            if(model_name.isNotEmpty() && model_no.isNotEmpty() && manufacturer_id > 0){
                addManafacturaAndCarRepository.addCarModel( id ,model_name, model_no, manufacturer_id)
            }
    }

    fun addCarModelJSON(id:Int?, model_name:String, model_no:String, manufacturer_id:Int){
        createNewCarModele.postValue(null)
        addManafacturaAndCarRepository.addCarModelJSON(id, model_name, model_no, manufacturer_id, createNewCarModele, requestState)
    }

}