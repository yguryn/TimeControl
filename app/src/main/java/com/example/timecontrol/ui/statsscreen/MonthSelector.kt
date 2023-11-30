package com.example.timecontrol.ui.statsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timecontrol.R

@Composable
fun MonthSelector(
    selectedMonth: String,
    setOpen: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentWidth()
            .clickable { setOpen(true) }
            .padding(start = 10.dp, top = 10.dp)
    ) {
        Text(
            text = selectedMonth,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_calendar_today_24),
            contentDescription = "Calendar select month and year",
            modifier = Modifier.size(30.dp)
        )
    }
}