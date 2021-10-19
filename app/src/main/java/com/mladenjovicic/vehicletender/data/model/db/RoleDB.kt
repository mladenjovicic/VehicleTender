package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "role", indices = arrayOf(Index(value = arrayOf("ServerId"), unique = true)))
data class RoleDB(
    @ColumnInfo(name="ServerId")
    var ServerId:String,
    @ColumnInfo(name ="RoleId")
    var RoleId:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}