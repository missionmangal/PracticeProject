package com.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class MovieData(
        @ColumnInfo var title:String?="",
        @ColumnInfo var des:String?="") {
}