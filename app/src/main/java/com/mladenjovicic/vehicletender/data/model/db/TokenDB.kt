package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName = "token_auto_login", indices = arrayOf(Index(value = arrayOf("userName"), unique = true)))
data class TokenDB(
                    @ColumnInfo(name = "idSer")
                    var IdSer:Int?=null,
                    @ColumnInfo(name = "access_token")
                   var access_token:String,
                   @ColumnInfo(name ="token_type")
                   var token_type:String,
                   @ColumnInfo(name ="expires_in")
                   var expires_in:String,
                   @ColumnInfo(name ="userName")
                   var userName:String,
                   @ColumnInfo(name ="issued")
                   var issued:String,
                   @ColumnInfo(name ="expires")
                   var expires:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null

}