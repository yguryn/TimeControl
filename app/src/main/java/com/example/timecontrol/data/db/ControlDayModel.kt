package com.example.timecontrol.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "controldays")
data class ControlDayModel(
    var day: Int,
    var month: Int,
    var year: Int,
    var comeTime: String = "",
    var leaveTime: String = "",
) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = year * 10000 + month * 100 + day
}
