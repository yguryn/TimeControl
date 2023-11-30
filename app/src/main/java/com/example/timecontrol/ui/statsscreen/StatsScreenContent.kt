package com.example.timecontrol.ui.statsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatsScreenContent(
    open: Boolean,
    setOpen: (Boolean) -> Unit,
    selectedMonth: String,
    setSelectedMonth: (String) -> Unit,
    averageComeTime: String,
    averageLeaveTime: String,
    averageWorkTime: String,
    comeTimeListForDisplay: List<Pair<Int, Float>>,
    leaveTimeListForDisplay: List<Pair<Int, Float>>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 56.dp)
            .background(Color.Blue)
    ) {
        MonthSelector(selectedMonth, setOpen)

        InfoText(text = "Average come time: $averageComeTime")
        InfoText(text = "Average leave time: $averageLeaveTime")
        InfoText(text = "Average work time: $averageWorkTime")

        if (open) {
            SelectMonthAndYearDialog(
                closeDialog = { setOpen(false) },
                selectedMonth = setSelectedMonth
            )
        }

        ComeChart(comeTimeListForDisplay)
        LeaveChart(leaveTimeListForDisplay)
    }
}

@Composable
private fun InfoText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
    )
}