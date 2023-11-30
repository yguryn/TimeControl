package com.example.timecontrol.ui.timescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timecontrol.data.db.ControlDayModel
import com.example.timecontrol.domain.DayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val dayRepository: DayRepository
) : ViewModel() {

    val comeTime: StateFlow<String> = MutableStateFlow("Select arrival time")
    val leaveTime: StateFlow<String> = MutableStateFlow("Select leave time")

    fun addControlDay(controlDay: ControlDayModel, action: UpdateAction) {
        viewModelScope.launch {
            if (dayRepository.isDayExist(controlDay.id)) {
                when (action) {
                    UpdateAction.UPDATE_COME_TIME -> dayRepository.updateComeTimeForDay(controlDay.id, controlDay.comeTime)
                    UpdateAction.UPDATE_LEAVE_TIME -> dayRepository.updateLeaveTimeForDay(controlDay.id, controlDay.leaveTime)
                }
            } else {
                dayRepository.insertNewDay(controlDay)
            }
        }
    }


    fun getComeTimeByDay(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            dayRepository.getComeTimeByDay(year, month, day)
                .collect { newComeTime ->
                    if (newComeTime != null) {
                        (comeTime as MutableStateFlow).value = newComeTime
                    } else {
                        (comeTime as MutableStateFlow).value = "Select arrival time"
                    }
                }
        }
    }

    fun getLeaveTimeByDay(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            dayRepository.getLeaveTimeByDay(year, month, day)
                .collect { newLeaveTime ->
                    if (!newLeaveTime.isNullOrEmpty()) {
                        (leaveTime as MutableStateFlow).value = newLeaveTime
                    } else {
                        (leaveTime as MutableStateFlow).value = "Select leave time"
                    }
                }
        }
    }
}