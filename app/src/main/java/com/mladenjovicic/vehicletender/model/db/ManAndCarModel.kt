package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ManAndCarModel(
        @ColumnInfo(name ="model_name")
        var model_name:String,
        @ColumnInfo(name ="model_no")
        var model_no:String,
        @ColumnInfo(name ="manufacturer_name")
        var manufacturer_name:String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}