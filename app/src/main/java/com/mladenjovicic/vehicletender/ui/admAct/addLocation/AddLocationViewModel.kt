package com.mladenjovicic.vehicletender.ui.admAct.addLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.API.RetrofitInstance
import com.mladenjovicic.vehicletender.data.API.RetrofitInterface
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import com.mladenjovicic.vehicletender.data.model.db.LocationModelDB
import com.mladenjovicic.vehicletender.data.repository.AdminRepository
import retrofit2.Response


class AddLocationViewModel(private val AddLocationRepository: AdminRepository) : ViewModel() {
    lateinit var createNewLocation: MutableLiveData<LocationModelAPI?>
    val requestState = MutableLiveData<RequestState>()

        init {
        createNewLocation = MutableLiveData()
        }

        fun getNewLocationObserver(): MutableLiveData<LocationModelAPI?> {
        return createNewLocation
        }

        fun addNewLocation( id:Int, newLocation:String, zipCode:String){

                AddLocationRepository.addNewLocation(id,newLocation,zipCode)

        }
        fun getListLocation ():LiveData<List<LocationModelDB>>{
            return AddLocationRepository.getListLocation()!!
        }

    fun addLocationJSON(id:Int?, city:String, zipCode: String){
        createNewLocation.postValue(null)
        AddLocationRepository.addLocationJSON(id, city,zipCode, requestState, createNewLocation )
    }

}