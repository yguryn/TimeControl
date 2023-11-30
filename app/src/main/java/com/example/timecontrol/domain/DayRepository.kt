package com.example.timecontrol.domain

import com.example.timecontrol.data.db.ControlDayModel
import kotlinx.coroutines.flow.Flow

interface DayRepository {

    fun getDayByMonth(selectedYear: Int, selectedMonth: Int): Flow<List<ControlDayModel>>

    fun getComeTimeByDay(year: Int, month: Int, day: Int): Flow<String?>

    fun getLeaveTimeByDay(year: Int, month: Int, day: Int): Flow<String?>

    suspend fun insertNewDay(controlDayModel: ControlDayModel)

    suspend fun updateDay(controlDayModel: ControlDayModel)

    suspend fun isDayExist(id: Int): Boolean

    suspend fun updateComeTimeForDay(id: Int, comeTime: String)

    suspend fun updateLeaveTimeForDay(id: Int, leaveTime: String)
}