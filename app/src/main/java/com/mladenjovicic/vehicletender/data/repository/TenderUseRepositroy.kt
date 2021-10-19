package com.mladenjovicic.vehicletender.data.repository

import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.API.RetrofitService
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.TenderStockModelAPI
import com.mladenjovicic.vehicletender.data.model.api.TenderUserModelAPI
import com.mladenjovicic.vehicletender.data.model.db.*
import com.mladenjovicic.vehicletender.data.repository.db.LocalRepository

class TenderUseRepositroy (private val retrofitService: RetrofitService,
                           private val localRepository: LocalRepository
){

    fun getTenderNo(tenderNo: String): LiveData<TenderModelDB>?{
        return localRepository.getTenderNo(tenderNo)
    }
    fun getCarStockListActive(isSold: Boolean):LiveData<List<stockCarList>>?{

        return localRepository.getCarStockListActive(isSold)
    }

    fun getCarStockUpdate(isSold: Boolean):LiveData<List<stockCarUpdate>>?{
        return  localRepository.getCarStockListUpdate(isSold)
    }

    fun insertTenderStock(serverId:Int,id:Int, tenderId:String?, saleDate:String, isDeleted:Boolean){
        localRepository.insertTenderStock(serverId, id, tenderId, saleDate, isDeleted)
    }
    fun deleteTenderStock(id:Int, tenderId:String, saleDate:String){
        localRepository.deleteTenderStock(id, tenderId, saleDate)
    }
    fun addTenderUser(
        serverId:Int,
        tenderId: Int,
        userId: String){
        localRepository.insertTenderUser(serverId, tenderId, userId)
    }
    fun addTenderStock(
            token: String,
            id: Int?,
            stockId:Int,
            tenderId:Int,
            saleDate:String,
            isDeleted:Boolean,
            liveData: MutableLiveData<TenderStockModelAPI?>,
            requestState: MutableLiveData<RequestState>){
        retrofitService.addTenderStockJSON(token, id, stockId, tenderId, saleDate, isDeleted, liveData, requestState)
    }

    fun updateTenderStockJSON(
            token: String,
            id: Int?,
            stockId:Int,
            tenderId:Int,
            saleDate:String,
            isDeleted:Boolean,
            liveData: MutableLiveData<TenderStockModelAPI?>,
            requestState: MutableLiveData<RequestState>
    ){
        retrofitService.updateTenderStockJSON(token, id, stockId, tenderId, saleDate, isDeleted, liveData, requestState)
    }

    fun readUserList(statusId:String):LiveData<List<UserModelDB>>{
        return localRepository.getUserList(statusId)
    }

    fun getUserListUser(statusId: String):LiveData<List<UserListUser>>{
        return localRepository.getUserListUser(statusId)
    }

    fun getUserListStatusId(statusId: String):LiveData<List<UserListStatusId>>{
        return  localRepository.getUserListStatusId(statusId)
    }

    fun readTenderUser():LiveData<List<TenderUserModelDB>>{
        return localRepository.readUserTender()
    }
    fun insertTenderUser(
        token: String,
        id: Int?,
        tenderId:Int,
        userId:String,
        liveData: MutableLiveData<TenderUserModelAPI?>,
        requestState: MutableLiveData<RequestState>
    ){
        return retrofitService.addTenderUserJSON(token, id, tenderId,userId,liveData,requestState)
    }
}