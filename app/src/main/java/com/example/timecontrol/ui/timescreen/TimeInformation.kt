package com.example.timecontrol.ui.timescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimeInformation(
    comeTime: String,
    leaveTime: String,
    onComeTimeSelected: (hour: Int, minute: Int) -> Unit,
    onLeaveTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    var showComeTimePicker by remember { mutableStateOf(false) }
    var showLeaveTimePicker by remember { mutableStateOf(false) }

    val comeTimeSplit = comeTime.split(":")
    val leaveTimeSplit = leaveTime.split(":")
    var comeTimeHour = 0
    var comeTimeMinutes = 0
    var leaveTimeHour = 0
    var leaveTimeMinutes = 0

    if (comeTimeSplit.size == 2 && comeTimeSplit[0].all { it.isDigit() }) {
        comeTimeHour = comeTimeSplit[0].toInt()
        comeTimeMinutes = comeTimeSplit[1].toInt()
    }

    if (leaveTimeSplit.size == 2 && leaveTimeSplit[0].all { it.isDigit() }) {
        leaveTimeHour = leaveTimeSplit[0].toInt()
        leaveTimeMinutes =leaveTimeSplit[1].toInt()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Come Time: $comeTime",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable { showComeTimePicker = true }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Leave Time: $leaveTime",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable { showLeaveTimePicker = true }
        )
    }

    if (showComeTimePicker) {
        TimePickerDialog(
            initialHour = comeTimeHour,
            initialMinute = comeTimeMinutes,
            onTimeSelected = { hour, minute ->
                onComeTimeSelected(hour, minute)
                showComeTimePicker = false
            },
            onDismiss = { showComeTimePicker = false }
        )
    }

    if (showLeaveTimePicker) {
        TimePickerDialog(
            initialHour = leaveTimeHour,
            initialMinute = leaveTimeMinutes,
            onTimeSelected = { hour, minute ->
                onLeaveTimeSelected(hour, minute)
                showLeaveTimePicker = false
            },
            onDismiss = { showLeaveTimePicker = false }
        )
    }
}
