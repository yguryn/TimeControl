package com.example.timecontrol.ui.statsscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
val initialCalendar: Calendar = Calendar.getInstance()

@Composable
fun StatsScreen(viewModel: StatsViewModel = hiltViewModel()) {
    val initialCalendar = Calendar.getInstance()
    val currentMonthName =
        SimpleDateFormat("MMMM", Locale.getDefault()).format(initialCalendar.time)

    val (open, setOpen) = remember { mutableStateOf(false) }
    val (selectedMonth, setSelectedMonth) = remember { mutableStateOf(currentMonthName) }

    val averageComeTime by viewModel.averageComeTime.collectAsState()
    val averageLeaveTime by viewModel.averageLeaveTime.collectAsState()
    val listOfDays by viewModel.listOfDaysByMonth.collectAsState()

    val averageWorkTime = findAverageWorkTime(averageComeTime, averageLeaveTime)

    val comeTimeListForDisplay = listOfDays.mapNotNull {
        it.comeTime.parseTime()?.let { (hour, minute) -> it.day to hour * 60 + minute }
    }

    val leaveTimeListForDisplay = listOfDays.mapNotNull {
        it.leaveTime.takeIf { it.isNotEmpty() }?.parseTime()
            ?.let { (hour, minute) -> it.day to hour * 60 + minute }
    }

    LaunchedEffect(Unit) {
        val year = initialCalendar[Calendar.YEAR]
        val month = initialCalendar[Calendar.MONTH] + 1
        viewModel.getAverageComeTimeByMonth(year, month)
        viewModel.getAverageLeaveTimeByMonth(year, month)
        viewModel.getListOfDaysByMonth(year, month)
    }

    StatsScreenContent(
        open = open,
        setOpen = setOpen,
        selectedMonth = selectedMonth,
        setSelectedMonth = setSelectedMonth,
        averageComeTime = averageComeTime,
        averageLeaveTime = averageLeaveTime,
        averageWorkTime = averageWorkTime,
        comeTimeListForDisplay = comeTimeListForDisplay,
        leaveTimeListForDisplay = leaveTimeListForDisplay
    )
}

private fun String.parseTime(): Pair<Float, Float>? {
    return split(":").takeIf { it.size == 2 }?.map(String::toFloat)?.let { it[0] to it[1] }
}

fun findAverageWorkTime(averageComeTime: String, averageLeaveTime: String): String {
    val timePattern = """\d{2}:\d{2}""".toRegex()

    if (!timePattern.matches(averageComeTime) || !timePattern.matches(averageLeaveTime)) {
        return "Cannot calculate average work time"
    }

    val averageComeTimeParts = averageComeTime.split(":").map { it.toInt() }
    val averageLeaveTimeParts = averageLeaveTime.split(":").map { it.toInt() }

    val averageComeTimeInMinutes = averageComeTimeParts[0] * 60 + averageComeTimeParts[1]
    val averageLeaveTimeInMinutes = averageLeaveTimeParts[0] * 60 + averageLeaveTimeParts[1]

    val averageWorkTimeInMinutes = averageLeaveTimeInMinutes - averageComeTimeInMinutes

    val hours = averageWorkTimeInMinutes / 60
    val minutes = averageWorkTimeInMinutes % 60

    return String.format("%02d:%02d", hours, minutes)
}


