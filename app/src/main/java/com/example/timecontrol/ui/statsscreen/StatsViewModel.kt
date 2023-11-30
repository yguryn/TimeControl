package com.example.timecontrol.ui.statsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timecontrol.data.db.ControlDayModel
import com.example.timecontrol.domain.DayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val dayRepository: DayRepository
) :  ViewModel() {

    val averageComeTime: MutableStateFlow<String> = MutableStateFlow("Cannot calculate average come time")
    val averageLeaveTime: MutableStateFlow<String> = MutableStateFlow("Cannot calculate average leave time")
    val listOfDaysByMonth: MutableStateFlow<List<ControlDayModel>> = MutableStateFlow(emptyList())

    fun getAverageComeTimeByMonth(selectedYear: Int, selectedMonth: Int) {
        viewModelScope.launch {
            val controlDayFlow = dayRepository.getDayByMonth(selectedYear, selectedMonth)
            controlDayFlow.collect { controlDayList ->
                listOfDaysByMonth.value = controlDayList
            }
        }
    }

    fun getListOfDaysByMonth(selectedYear: Int, selectedMonth: Int) {
        viewModelScope.launch {
            val controlDayFlow = dayRepository.getDayByMonth(selectedYear, selectedMonth)
            controlDayFlow.map { controlDayList ->
                controlDayList.mapNotNull { it.comeTime.split(":").takeIf { it.size == 2 } }
                    .map { it[0].toInt() * 60 + it[1].toInt() }
                    .average()
                    .let { average ->
                        if (average.isNaN()) {
                            "Cannot calculate average time"
                        } else {
                            val hours = (average / 60).toInt()
                            val minutes = average.toInt() % 60
                            "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
                        }
                    }
            }.collect { average ->
                averageComeTime.value = average
            }
        }
    }

    fun getAverageLeaveTimeByMonth(selectedYear: Int, selectedMonth: Int) {
        viewModelScope.launch {
            val controlDayFlow = dayRepository.getDayByMonth(selectedYear, selectedMonth)
            controlDayFlow.map { controlDayList ->
                controlDayList.mapNotNull { it.leaveTime.split(":").takeIf { it.size == 2 } }
                    .map { it[0].toInt() * 60 + it[1].toInt() }
                    .average()
                    .let { average ->
                        if (average.isNaN()) {
                            "Cannot calculate average time"
                        } else {
                            val hours = (average / 60).toInt()
                            val minutes = average.toInt() % 60
                            "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
                        }
                    }
            }.collect { average ->
                averageLeaveTime.value = average
            }
        }
    }
}