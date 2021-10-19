package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo

data class UserListUser(
                        @ColumnInfo(name ="tenderUsersId")
                        var tenderUsersId:Int,
                        @ColumnInfo(name ="tenderUsersServerId")
                        var tenderUsersServerId:Int,
                        @ColumnInfo(name ="tenderUsersTenderId")
                        var tenderUsersTenderId:String,
                        @ColumnInfo(name ="tenderUsersUserId")
                        var tenderUsersUserId:String,
                        @ColumnInfo(name ="tenderServerId")
                        var tenderServerId:Int,
                        @ColumnInfo(name ="tenderCretedBy")
                        var tenderCretedBy:Int,
                        @ColumnInfo(name ="tenderCloseDate")
                        var tenderCloseDate:Double,
                        @ColumnInfo(name ="tenderOpenDate")
                        var tenderOpenDate:Double,
                        @ColumnInfo(name ="tenderStatusId")
                        var tenderStatusId:String?,
                        @ColumnInfo(name ="userEmail")
                        var userEmail:String,
                        @ColumnInfo(name ="userUuid")
                        var userUuid:Boolean,
                        @ColumnInfo(name ="userStatususerl")
                        var userStatususerl:String,
                        @ColumnInfo(name ="userCompanyName")
                        var userCompanyName:String,
                        @ColumnInfo(name ="userContactName")
                        var userContactName:String?,
                        @ColumnInfo(name ="userContactSurname")
                        var userContactSurname:String) {
}