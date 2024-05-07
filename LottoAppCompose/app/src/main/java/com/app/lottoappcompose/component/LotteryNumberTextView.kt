package com.app.lottoappcompose.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.lottoappcompose.lottery.changeTextColor

@SuppressLint("ResourceAsColor")
@Composable
fun LotteryNumberTextView(text: Int, modifier: Modifier) {

    Box(
        modifier = modifier
            .background(
                color = changeTextColor(text), // 원형 백그라운드의 색상
                shape = CircleShape // 원형 모양으로 설정
            )
            .size(50.dp) // 원형 백그라운드의 크기 설정
        , contentAlignment = Alignment.Center // 가운데 정렬

    ) {
        Text(
            text = text.toString(),
            color = Color.White, // 텍스트의 색상
            modifier = modifier
                .fillMaxSize()
                .padding(top = 13.dp),
            textAlign = TextAlign.Center,
        )

    }
}