package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tenderStock")
data class TenderStockModelDB(
        @ColumnInfo(name ="stockId")
        var stockId:Int,
        @ColumnInfo(name ="tenderId")
        var tenderId:Int,
        @ColumnInfo(name ="saleDate")
        var saleDate:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}