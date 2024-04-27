package com.app.lottoappcompose.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.lottoappcompose.R
import com.app.lottoappcompose.util.DashedLine
import com.app.lottoappcompose.viewmodel.LotteryViewModel

@Composable
fun LotteryScreen(modifier: Modifier) {
    val viewModel: LotteryViewModel =viewModel()
    val context = LocalContext.current
    var showExcludedNumberDialog by remember { mutableStateOf(false) }
    var showFixNumberDialog by remember { mutableStateOf(false) }
    var generateRandomNumbers by remember {
        mutableStateOf(viewModel.randomNumbers)
    }

    val onClickLotteryButton = {
        generateRandomNumbers = viewModel.generateRandomNumbers()
    }
    val excludedNumberDialogClick ={
        showExcludedNumberDialog = false
    }
    val fixNumberDialogClick ={
        showFixNumberDialog = false
    }

    if (showExcludedNumberDialog) {
        CustomExcludeNumberDialog(excludedNumberDialogClick,modifier,viewModel,context)
    }
    if (showFixNumberDialog) {
        CustomFixNumberDialog(fixNumberDialogClick,modifier,viewModel,context)
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
                    Button(onClick = { showExcludedNumberDialog = true }) {
                        Text(text = "제외수", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(modifier = modifier.width(4.dp))
                    if (viewModel.excludedNumberSet.isNotEmpty()){
                        for (data in viewModel.excludedNumberSet){
                            CircularExcludedNumberTextView(text = data.toString(),modifier,"exclude")
                            Spacer(modifier = modifier.width(4.dp))
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = { showFixNumberDialog = true }) {
                        Text(text = "고정수", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(modifier = modifier.width(4.dp))
                    if (viewModel.fixNumberSet.isNotEmpty()){
                        for (data in viewModel.fixNumberSet){
                            CircularExcludedNumberTextView(text = data.toString(),modifier,"fix")
                            Spacer(modifier = modifier.width(4.dp))
                        }
                    }
                }
            }
            DashedLine()
            Column(modifier.fillMaxWidth()) {

                generateRandomNumbers.forEach {numbers ->
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        numbers.forEach {number ->
                            LotteryNumberTextView(number,modifier)
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
                Button(onClick = onClickLotteryButton) {
                    Text(text = "번호 추첨")
                }
            }


        }
    }

}

@Composable
fun CircularExcludedNumberTextView(text: String, modifier: Modifier,type : String) {
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

@Composable
@SuppressLint("ResourceAsColor")
fun changeTextColor(text: Int): Color {
    val color: Color = when {
        text <= 10 -> colorResource(id = R.color.yello)
        text <= 20 -> colorResource(R.color.blue)
        text <= 30 -> colorResource(R.color.red)
        text <= 40 -> colorResource(R.color.gray)
        text <= 45 -> colorResource(R.color.green)
        else -> Color.LightGray // 기본값 설정
    }
    return color
}


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

@Composable
fun CustomFixNumberDialog(
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
                Text(text = "고정수를 선택해주세요", style = MaterialTheme.typography.headlineLarge)
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
                                        color = if (viewModel.fixButtonState[index]) {
                                            Color.Blue
                                        } else {
                                            Color.Gray
                                        }, // 원형 백그라운드의 색상
                                        shape = CircleShape // 원형 모양으로 설정
                                    )
                                    .size(40.dp) // 원형 백그라운드의 크기 설정
                                    .clickable(enabled = buttonEnabled) {
                                        if (viewModel.fixNumberSet.size >= 5) {
                                            buttonEnabled = false
                                            Toast
                                                .makeText(
                                                    context,
                                                    "고정수는 최대 5개까지 설정 가능합니다",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                            return@clickable
                                        }
                                        viewModel.changeFixButtonState(index)
                                        viewModel.addFixNumber(number)

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
                    Text("고정수 저장")
                }
            }
        }
    }
}