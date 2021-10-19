package com.mladenjovicic.vehicletender.ui.mainAct.mainAct

import androidx.lifecycle.ViewModel
import com.mladenjovicic.vehicletender.data.repository.MainRepository

class MainActivityViewModel(val mainRepository: MainRepository):ViewModel() {


    fun deleteAll(){
        mainRepository.deleteAll()
    }
}