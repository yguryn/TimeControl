package com.example.timecontrol.ui.statsscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timecontrol.ui.statsscreen.formatters.DayAxisValueFormatter
import com.example.timecontrol.ui.statsscreen.formatters.TimeAxisValueFormatter
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun ComeChart(newArr: List<Pair<Int, Float>>) {
    Chart(
        chart = columnChart(
            axisValuesOverrider = AxisValuesOverrider.fixed(
                minY = newArr.minOfOrNull { it.second - 60 },
                maxY = newArr.maxOfOrNull { it.second + 60 },
            ),
        ).apply {
            addDecoration(
                ThresholdLine(
                    thresholdValue = 600f,
                    thresholdLabel = "10 : 00",
                    lineComponent = shapeComponent(color = Color.Red),
                    labelComponent = textComponent(
                        color = Color.Red,
                        padding = dimensionsOf(horizontal = 8.dp),
                        textSize = 12.sp
                    ),
                )
            )
        },
        model = entryModelOf(entriesOf(*newArr.toTypedArray())),
        startAxis = startAxis(valueFormatter = TimeAxisValueFormatter()),
        bottomAxis = bottomAxis(valueFormatter = DayAxisValueFormatter()),
    )
}