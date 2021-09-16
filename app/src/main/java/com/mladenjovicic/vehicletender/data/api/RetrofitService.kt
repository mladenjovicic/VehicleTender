package com.mladenjovicic.vehicletender.data.api

import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.vehicletender.data.model.RequestState
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitService(private val retrofitInstance: RetrofitInstance) {
    private val retrofitInterface = retrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

    fun getLocations(
            liveData: MutableLiveData<List<LocationModelAPI>>,
            requestState: MutableLiveData<RequestState>
    ) {
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getLocationList()
        call.enqueue(object : Callback<List<LocationModelAPI>> {
            override fun onResponse(call: Call<List<LocationModelAPI>>, response: Response<List<LocationModelAPI>>) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)

            }

            override fun onFailure(call: Call<List<LocationModelAPI>>, t: Throwable) {
                requestState.postValue(
                        RequestState(
                                pending = false,
                                successful = false,
                                errorMessage = t.message.toString()
                        )
                )
            }
        })
    }
}