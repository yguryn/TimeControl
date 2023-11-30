package com.example.timecontrol.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {

    @Query("SELECT * FROM controldays WHERE year = :selectedYear AND month = :selectedMonth")
    fun getDaysBySelectedMonth(selectedYear: Int, selectedMonth: Int): Flow<List<ControlDayModel>>

    @Query("SELECT comeTime FROM controldays WHERE year = :year AND month = :month AND day = :day")
    fun getComeTimeByDay(year: Int, month: Int, day: Int): Flow<String?>

    @Query("SELECT leaveTime FROM controldays WHERE year = :year AND month = :month AND day = :day")
    fun getLeaveTimeByDay(year: Int, month: Int, day: Int): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(controlDay: ControlDayModel)

    @Update
    suspend fun update(controlDay: ControlDayModel)

    @Query("UPDATE controldays SET comeTime = :comeTime WHERE id = :id")
    suspend fun updateComeTime(id: Int, comeTime: String)

    @Query("UPDATE controldays SET leaveTime = :leaveTime WHERE id = :id")
    suspend fun updateLeaveTime(id: Int, leaveTime: String)

    @Query("SELECT EXISTS(SELECT * FROM controldays WHERE id = :id)")
    suspend fun isControlDayExists(id: Int): Boolean
}
