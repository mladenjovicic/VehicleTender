package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "stockInfo", indices = arrayOf(Index(value = arrayOf("serverId"), unique = true)))
data class StockInfoModelDB(
                            @ColumnInfo(name = "serverId")
                            var serverId:Int,
                            @ColumnInfo(name ="year")
                     var year:Int,
                            @ColumnInfo(name ="modelLineId")
                     var modedLineId:Int,
                            @ColumnInfo(name ="mileage")
                     var mileage:Int,
                            @ColumnInfo(name ="price")
                     var price:Double,
                            @ColumnInfo(name ="comments")
                     var comments:String,
                            @ColumnInfo(name ="locationId")
                     var locationId:Int,
                            @ColumnInfo(name ="regNo")
                     var regNo:String?=null,
                            @ColumnInfo(name ="isSold")
                     var isSold:Boolean) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}
