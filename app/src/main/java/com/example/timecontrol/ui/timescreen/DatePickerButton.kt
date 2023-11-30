package com.example.timecontrol.ui.timescreen

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timecontrol.R
import com.example.timecontrol.ui.theme.Typography

@Composable
fun DatePickerButton(
    selectedDateText: String,
    year: Int,
    month: Int,
    dayOfMonth: Int,
    onDateSelected: (Int, Int, Int) -> Unit
) {
    val context = LocalContext.current
    val datePicker = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth -> onDateSelected(year, month, dayOfMonth) },
        year, month, dayOfMonth
    )

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .clickable { datePicker.show() },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = selectedDateText.ifEmpty { "$dayOfMonth-${month + 1}-$year" },
            style = Typography.bodyMedium
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_calendar_today_24),
            contentDescription = "Calendar Icon",
        )
    }
}