package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "status", indices = arrayOf(Index(value = arrayOf("idServer"), unique = true)))
data class StatusModelDB(
    @ColumnInfo(name = "idServer")
        var idServer:Int?=null,
        @ColumnInfo(name ="statusType")
        var statusType:String
        ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}