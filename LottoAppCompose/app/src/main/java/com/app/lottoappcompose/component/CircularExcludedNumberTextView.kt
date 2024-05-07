package com.app.lottoappcompose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CircularExcludedNumberTextView(text: String, modifier: Modifier, type : String) {
    Box(
        modifier = modifier
            .background(
                color = if (type == "exclude") Color.Red else Color.Blue, // 원형 백그라운드의 색상
                shape = CircleShape // 원형 모양으로 설정
            )
            .size(24.dp) // 원형 백그라운드의 크기 설정

    ) {
        Text(
            text = text,
            color = Color.White, // 텍스트의 색상
            modifier = modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )

    }
}