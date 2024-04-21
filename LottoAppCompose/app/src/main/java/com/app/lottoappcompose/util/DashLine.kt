package com.app.lottoappcompose.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 1.5.dp,
    dashWidth: Dp = 5.dp,
    dashGap: Dp = 7.dp
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(1.5.dp).padding(horizontal = 10.dp)) {
        val path = Path()
        path.moveTo(0f, size.height / 2)
        var x = 0f
        while (x < size.width) {
            path.lineTo(x, size.height / 2)
            x += dashWidth.toPx()
            path.moveTo(x, size.height / 2)
            x += dashGap.toPx()
        }
        drawPath(path, color, style = Stroke(width = strokeWidth.toPx()))
    }
}