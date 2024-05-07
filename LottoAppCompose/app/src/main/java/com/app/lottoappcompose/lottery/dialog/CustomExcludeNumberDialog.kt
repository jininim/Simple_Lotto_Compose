package com.app.lottoappcompose.lottery.dialog

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.lottoappcompose.lottery.LotteryViewModel

@Composable
fun CustomExcludeNumberDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    viewModel: LotteryViewModel,
    context: Context
) {

    var buttonEnabled by remember {
        mutableStateOf(true)
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
                .background(color = Color.White)) {
            Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "제외수를 선택해주세요", style = MaterialTheme.typography.headlineLarge)
            }

            Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                for (i in 1..9) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        for (j in 1..5) {
                            val number = (i - 1) * 5 + j
                            val index = (i - 1) * 5 + j - 1
                            Box(
                                modifier = modifier
                                    .background(
                                        color = if (viewModel.exButtonState[index]) {
                                            Color.Red
                                        } else {
                                            Color.Gray
                                        }, // 원형 백그라운드의 색상
                                        shape = CircleShape // 원형 모양으로 설정
                                    )
                                    .size(40.dp) // 원형 백그라운드의 크기 설정
                                    .clickable(enabled = buttonEnabled) {
                                        if (viewModel.excludedNumberSet.size >= 5) {
                                            buttonEnabled = false
                                            Toast
                                                .makeText(
                                                    context,
                                                    "제외수는 최대 5개까지 설정 가능합니다",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                            return@clickable
                                        }
                                        viewModel.changeExButtonState(index)
                                        viewModel.addExcludedNumber(number)

                                    }
                                , contentAlignment = Alignment.Center // 가운데 정렬

                            ) {
                                Text(
                                    text = number.toString(),
                                    color = Color.White, // 텍스트의 색상
                                    modifier = modifier
                                        .fillMaxSize()
                                        .padding(top = 8.dp),
                                    textAlign = TextAlign.Center,
                                )

                            }
                        }
                    }
                }
            }
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Button(onClick = onDismissRequest) {
                    Text("제외수 저장")
                }
            }
        }
    }
}