package com.example.timecontrol.ui.timescreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.timecontrol.data.db.ControlDayModel
import java.util.Calendar

@Composable
fun NotificationAlertDialog(
    openDialog: MutableState<Boolean>,
    pickedYear: Int,
    pickedMonth: Int,
    pickedDay: Int,
    viewModel: TimeViewModel = hiltViewModel(),
    ) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Set Time") },
            text = {
                Text("Do you want to set the come time to the current time?")
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addControlDay(
                        ControlDayModel(
                            pickedDay,
                            pickedMonth,
                            pickedYear,
                            "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:" +
                                    "${Calendar.getInstance().get(Calendar.MINUTE)}",
                            ""
                        ), UpdateAction.UPDATE_COME_TIME
                    )
                    openDialog.value = false
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}