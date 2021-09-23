package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenderStock")
data class TenderStockModelDB(
        @ColumnInfo(name = "serverId")
        var serverId:Int,
        @ColumnInfo(name ="stockId")
        var stockId:Int,
        @ColumnInfo(name ="tenderId")
        var tenderId:String,
        @ColumnInfo(name ="saleDate")
        var saleDate:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}