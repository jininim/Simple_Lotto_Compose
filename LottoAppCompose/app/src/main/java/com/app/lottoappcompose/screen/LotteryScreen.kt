package com.app.lottoappcompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.lottoappcompose.util.DashedLine
import com.app.lottoappcompose.viewmodel.LotteryViewModel

@Composable
fun LotteryScreen(modifier: Modifier) {
    val viewModel: LotteryViewModel =viewModel()

    var showDialog by remember { mutableStateOf(false) }


    val dialogClick ={
        showDialog = false
    }

    if (showDialog) {
        CustomNumberDialog(dialogClick,modifier,viewModel)
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "로또번호추첨기",
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Column(
            modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Lotto 6/45", style = MaterialTheme.typography.bodyLarge)
            }
            DashedLine()
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text("번호를 추첨해주세요", style = MaterialTheme.typography.bodySmall)
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier.padding(horizontal = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = { showDialog = true }) {
                        Text(text = "제외수", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(modifier = modifier.width(4.dp))
                    if (viewModel.excludedNumberList.isNotEmpty()){
                        for (i in 0..<viewModel.excludedNumberList.size){
                            CircularExcludedNumberTextView(text = viewModel.excludedNumberList[i].toString(),modifier)
                            Spacer(modifier = modifier.width(4.dp))
                        }
                    }
                }
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "고정수", style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
            DashedLine()
            Column(modifier.fillMaxWidth()) {
                for (j in 1..5) {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (i in 1..5) {
                            LotteryNumberTextView("1",modifier)
                        }

                    }
                    DashedLine()
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(4.dp), horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "번호 추첨")
                }
            }


        }
    }

}

@Composable
fun CircularExcludedNumberTextView(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.error, // 원형 백그라운드의 색상
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

@Composable
fun LotteryNumberTextView(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.error, // 원형 백그라운드의 색상
                shape = CircleShape // 원형 모양으로 설정
            )
            .size(50.dp) // 원형 백그라운드의 크기 설정
        , contentAlignment = Alignment.Center // 가운데 정렬

    ) {
        Text(
            text = text,
            color = Color.White, // 텍스트의 색상
            modifier = modifier.fillMaxSize().padding(top = 13.dp),
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
fun LotteryDialogTextView(text: String, modifier: Modifier,color : Color) {
    Box(
        modifier = modifier
            .background(
                color = color, // 원형 백그라운드의 색상
                shape = CircleShape // 원형 모양으로 설정
            )
            .size(50.dp) // 원형 백그라운드의 크기 설정
        , contentAlignment = Alignment.Center // 가운데 정렬

    ) {
        Text(
            text = text,
            color = Color.White, // 텍스트의 색상
            modifier = modifier.fillMaxSize().padding(top = 13.dp),
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
fun CustomNumberDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    viewModel: LotteryViewModel
) {

    val buttonState = remember { mutableStateListOf<Boolean>() }
    if (buttonState.size < 45){
        for (i in 1..45){
            buttonState.add(false)
        }
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
                                        color = if(buttonState[index]) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            Color.Gray
                                        }, // 원형 백그라운드의 색상
                                        shape = CircleShape // 원형 모양으로 설정
                                    )
                                    .size(40.dp) // 원형 백그라운드의 크기 설정
                                    .clickable {
                                        buttonState[index] = !buttonState[index]
                                        viewModel.addNumber(number)
                                    }
                                , contentAlignment = Alignment.Center // 가운데 정렬

                            ) {
                                Text(
                                    text = number.toString(),
                                    color = Color.White, // 텍스트의 색상
                                    modifier = modifier.fillMaxSize().padding(top = 8.dp),
                                    textAlign = TextAlign.Center,
                                )

                            }
                          /*  Button(
                                onClick = {
                                    buttonState[index] = !buttonState[index]
                                    viewModel.addNumber(number)
                                } ,
                                modifier = modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(buttonState[index]) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.Gray
                                    }
                                )
                            ) {
                                Text(text = number.toString(), style = MaterialTheme.typography.bodySmall)
                            }*/

                        }
                    }
                }
            }
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Button(onClick = onDismissRequest) {
                    Text("제외 수 저장")
                }
            }
        }
    }
}