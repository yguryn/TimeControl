package com.example.timecontrol.ui.timescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.timecontrol.data.db.ControlDayModel
import java.util.Calendar

@Composable
fun TimeScreen(isAppOpenedFromNotification: Boolean, viewModel: TimeViewModel = hiltViewModel()) {
    val calendar = Calendar.getInstance()

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    var selectedDateText by remember { mutableStateOf("") }
    val comeTime by viewModel.comeTime.collectAsState()
    val leaveTime by viewModel.leaveTime.collectAsState()

    var pickedYear by remember { mutableStateOf(year) }
    var pickedMonth by remember { mutableStateOf(month + 1) }
    var pickedDay by remember { mutableStateOf(dayOfMonth) }

    LaunchedEffect(Unit){
        viewModel.getComeTimeByDay(year, month + 1, dayOfMonth)
        viewModel.getLeaveTimeByDay(year, month + 1, dayOfMonth)
    }

    val openNotificationDialog = remember { mutableStateOf(isAppOpenedFromNotification) }

    if (openNotificationDialog.value) {
        NotificationAlertDialog(
            openNotificationDialog,
            pickedYear,
            pickedMonth,
            pickedDay
        )
    }
    Column(
        Modifier
            .background(Color.Blue)
            .fillMaxSize()
    ) {

        DatePickerButton(selectedDateText, year, month, dayOfMonth) { year, month, day ->
            selectedDateText = "$day-${month + 1}-$year"
            viewModel.getComeTimeByDay(year, month + 1, day)
            viewModel.getLeaveTimeByDay(year, month + 1, day)
            pickedYear = year
            pickedMonth = month + 1
            pickedDay = day
        }

        TimeInformation(
            comeTime,
            leaveTime,
            onComeTimeSelected = { hour, minute ->
                viewModel.addControlDay(
                    ControlDayModel(
                        day = pickedDay,
                        month = pickedMonth,
                        year = pickedYear,
                        comeTime = "$hour:$minute"
                    ),
                    UpdateAction.UPDATE_COME_TIME
                )
                viewModel.getComeTimeByDay(year, month + 1, dayOfMonth)
            },
            onLeaveTimeSelected = { hour, minute ->
                viewModel.addControlDay(
                    ControlDayModel(
                        day = pickedDay,
                        month = pickedMonth,
                        year = pickedYear,
                        leaveTime = "$hour:$minute"
                    ),
                    UpdateAction.UPDATE_LEAVE_TIME
                )
                viewModel.getLeaveTimeByDay(year, month + 1, dayOfMonth)
            })
    }
}
