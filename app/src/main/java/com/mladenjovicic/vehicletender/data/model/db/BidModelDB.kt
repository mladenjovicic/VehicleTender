package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "bid", indices = arrayOf(Index(value = arrayOf("serverId"), unique = true)))
data class BidModelDB(
        @ColumnInfo(name="serverId")
        var serverId:Int,
        @ColumnInfo(name ="userId")
        var userId:String,
        @ColumnInfo(name ="stockId")
        var stockId:Int,
        @ColumnInfo(name ="price")
        var price:Double,
        @ColumnInfo(name ="isWinningPrice")
        var isWinningPrice:Boolean,
        @ColumnInfo(name = "isActive")
        var isActive:Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}