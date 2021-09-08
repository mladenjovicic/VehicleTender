package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tenderUser")
data class TenderUserModelDB(
                      @ColumnInfo(name ="tenderId")
                      var tenderId:Int,
                      @ColumnInfo(name ="userId")
                      var userId:String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}
