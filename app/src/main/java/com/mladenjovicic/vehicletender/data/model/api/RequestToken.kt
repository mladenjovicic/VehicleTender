package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class RequestToken(
@SerializedName("grant_type") var grant_type: String?,
@SerializedName("username") var username:String?,
@SerializedName("password") var password: String?) {
}