package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bid")
data class BidModelDB(
        @ColumnInfo(name ="userId")
        var userId:String,
        @ColumnInfo(name ="stockId")
        var stockId:Int,
        @ColumnInfo(name ="price")
        var price:Double,
        @ColumnInfo(name ="isWinningPrice")
        var isWinningPrice:Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}