package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class GetTokenAPI(
    @SerializedName("access_token") var access_token: String?,
    @SerializedName("token_type") var token_type:String?,
    @SerializedName("expires_in") var expires_in: Int?,
    @SerializedName(".issued") var issued: String?,
    @SerializedName(".expires") var expires: String,

) {
}