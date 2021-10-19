package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo

data class stockCarList(
                        @ColumnInfo(name = "id")
                        var Id:Int?=null,
                        @ColumnInfo(name = "serverId")
                        var serverId:Int,
                        @ColumnInfo(name ="year")
                        var year:Int,
                        @ColumnInfo(name ="mileage")
                        var mileage:Int,
                        @ColumnInfo(name ="price")
                        var price:Double,
                        @ColumnInfo(name ="comments")
                        var comments:String,
                        @ColumnInfo(name ="regNo")
                        var regNo:String?=null,
                        @ColumnInfo(name ="isSold")
                        var isSold:Boolean,
                        @ColumnInfo(name ="city")
                        var city:String?,
                        @ColumnInfo(name ="model_name")
                        var model_name:String,
                        @ColumnInfo(name ="model_no")
                        var model_no:String,
                        @ColumnInfo(name ="manufacturer_name")
                        var manufacturer_name:String) {



}