package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo

data class UserListStatusId(
    @ColumnInfo(name ="uuId")
    var uuId:String,
    @ColumnInfo(name ="contact_name")
    var contact_name:String?,
    @ColumnInfo(name ="contact_surname")
    var contact_surname:String?,
    @ColumnInfo(name ="status_user")
    var status_user:String?,
    @ColumnInfo(name ="email")
    var email:String?,
    @ColumnInfo(name ="company_name")
    var company_name:String?,
    @ColumnInfo(name ="tenderId")
    var tenderId:Int?,
    @ColumnInfo(name ="userId")
    var tenderOpenDate:String?,
    @ColumnInfo(name ="serverId")
    var serverId:Int?
    ) {
}