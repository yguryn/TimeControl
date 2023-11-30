package com.example.timecontrol.ui.statsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.dt.composedatepicker.CalendarType
import com.dt.composedatepicker.ComposeCalendar
import com.dt.composedatepicker.MonthViewType
import com.dt.composedatepicker.SelectDateListener
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun SelectMonthAndYearDialog(
    closeDialog: () -> Unit,
    selectedMonth: (String) -> Unit,
    viewModel: StatsViewModel = hiltViewModel(),
    ) {

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
    calendar.set(Calendar.MONTH, calendar[Calendar.MONTH] + 1)

    val calendarMax = Calendar.getInstance()
    calendarMax.set(Calendar.YEAR, calendar[Calendar.YEAR] + 3)
    calendarMax.set(Calendar.MONTH, 9)

    Dialog(onDismissRequest = { closeDialog.invoke() }) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            ComposeCalendar(
                minDate = calendar.time,
                maxDate = calendarMax.time,
                initialDate = initialCalendar.time,
                locale = Locale("en"),
                title = "Select Date",
                buttonTextSize = 15.sp,
                calendarType = CalendarType.ONE_SCREEN_MONTH_AND_YEAR,
                monthViewType = MonthViewType.ONLY_NUMBER_ONE_COLUMN,
                listener = object : SelectDateListener {
                    override fun onDateSelected(date: Date) {
                        val calendar = Calendar.getInstance()
                        calendar.time = date
                        val monthName = monthFormat.format(calendar.time)
                        selectedMonth.invoke(monthName)
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH) + 1
                        viewModel.getAverageComeTimeByMonth(year, month)
                        viewModel.getAverageLeaveTimeByMonth(year, month)
                        viewModel.getListOfDaysByMonth(year, month)
                        closeDialog.invoke()
                    }

                    override fun onCanceled() {
                        closeDialog.invoke()
                    }
                })
        }
    }
}