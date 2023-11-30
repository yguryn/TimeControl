package com.example.timecontrol.data.repository

import com.example.timecontrol.data.db.ControlDayModel
import com.example.timecontrol.data.db.DayDao
import com.example.timecontrol.domain.DayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DayRepositoryImpl @Inject constructor(private val dayDao: DayDao) : DayRepository {

    override fun getDayByMonth(selectedYear: Int, selectedMonth: Int): Flow<List<ControlDayModel>> =
        dayDao.getDaysBySelectedMonth(selectedYear, selectedMonth)

    override fun getComeTimeByDay(year: Int, month: Int, day: Int) =
        dayDao.getComeTimeByDay(year, month, day)

    override fun getLeaveTimeByDay(year: Int, month: Int, day: Int) =
        dayDao.getLeaveTimeByDay(year, month, day)

    override suspend fun insertNewDay(controlDayModel: ControlDayModel) =
        dayDao.insert(controlDayModel)

    override suspend fun updateDay(controlDayModel: ControlDayModel) =
        dayDao.update(controlDayModel)

    override suspend fun isDayExist(id: Int): Boolean = dayDao.isControlDayExists(id)

    override suspend fun updateComeTimeForDay(id: Int, comeTime: String) {
        dayDao.updateComeTime(id, comeTime)
    }

    override suspend fun updateLeaveTimeForDay(id: Int, leaveTime: String) {
        dayDao.updateLeaveTime(id, leaveTime)
    }
}