package com.app.lottoappcompose.lottery

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.lottoappcompose.R
import com.app.lottoappcompose.component.CircularExcludedNumberTextView
import com.app.lottoappcompose.component.DashedLine
import com.app.lottoappcompose.component.LotteryNumberTextView
import com.app.lottoappcompose.lottery.dialog.CustomExcludeNumberDialog
import com.app.lottoappcompose.lottery.dialog.CustomFixNumberDialog

@Composable
fun LotteryScreen(modifier: Modifier) {
    val viewModel: LotteryViewModel =viewModel()
    val context = LocalContext.current
    var showExcludedNumberDialog by remember { mutableStateOf(false) }
    var showFixNumberDialog by remember { mutableStateOf(false) }
    var generateRandomNumbers by remember {
        mutableStateOf(viewModel.randomNumbers)
    }
    //로또 추첨 번호 클릭시 randomNumbers 생성
    val onClickLotteryButton = {
        generateRandomNumbers = viewModel.generateRandomNumbers()
    }
    //제외수 Dialog 종료 시
    val onExcludeNumberDialogDismissRequest ={
        showExcludedNumberDialog = false
    }
    //제외수 버튼 클릭 시
    val onExcludeNumberButtonClick ={
        showExcludedNumberDialog = true
    }
    //고정수 Dialog 종료 시
    val onFixNumberDialogDismissRequest ={
        showFixNumberDialog = false
    }
    //고정수 버튼 클릭 시
    val onFixNumberButtonClick ={
        showFixNumberDialog = true
    }


    if (showExcludedNumberDialog) {
        //버튼 클릭 시 다이어로그 생성
        CustomExcludeNumberDialog(onExcludeNumberDialogDismissRequest,modifier,viewModel,context)
    }
    if (showFixNumberDialog) {
        //버튼 클릭 시 다이어로그 생성
        CustomFixNumberDialog(onFixNumberDialogDismissRequest,modifier,viewModel,context)
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        //로또 번호 추첨 Title
        LotteryTitle(modifier)
        //로또 번호 추첨 Body
        LotteryBody(
            modifier,
            onExcludeNumberButtonClick,
            viewModel,
            onFixNumberButtonClick,
            generateRandomNumbers,
            onClickLotteryButton
        )
    }

}

@Composable
private fun LotteryBody(
    modifier: Modifier,
    showExcludedNumberDialog: () -> Unit,
    viewModel: LotteryViewModel,
    showFixNumberDialog: () -> Unit,
    generateRandomNumbers: List<List<Int>>,
    onClickLotteryButton: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        LotterySmallTitle(modifier) // small title
        DashedLine() //util Dash line
        ShowDialogButton(modifier, showExcludedNumberDialog, viewModel, showFixNumberDialog) //제외수 , 고정수 버튼
        DashedLine() //util Dash line
        ShowRandomNumber(modifier, generateRandomNumbers, onClickLotteryButton) //로또 번호 생성 및 번호 추첨 버튼
    }
}

@Composable
private fun ShowRandomNumber(
    modifier: Modifier,
    generateRandomNumbers: List<List<Int>>,
    onClickLotteryButton: () -> Unit
) {
    Column(modifier.fillMaxWidth()) {

        generateRandomNumbers.forEach { numbers ->
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                numbers.forEach { number ->
                    LotteryNumberTextView(number, modifier)
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

@Composable
private fun ShowDialogButton(
    modifier: Modifier,
    showExcludedNumberDialog: () -> Unit,
    viewModel: LotteryViewModel,
    showFixNumberDialog: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = showExcludedNumberDialog) {
                Text(text = "제외수", style = MaterialTheme.typography.labelMedium)
            }
            Spacer(modifier = modifier.width(4.dp))
            if (viewModel.excludedNumberSet.isNotEmpty()) {
                for (data in viewModel.excludedNumberSet) {
                    CircularExcludedNumberTextView(text = data.toString(), modifier, "exclude")
                    Spacer(modifier = modifier.width(4.dp))
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = showFixNumberDialog) {
                Text(text = "고정수", style = MaterialTheme.typography.labelMedium)
            }
            Spacer(modifier = modifier.width(4.dp))
            if (viewModel.fixNumberSet.isNotEmpty()) {
                for (data in viewModel.fixNumberSet) {
                    CircularExcludedNumberTextView(text = data.toString(), modifier, "fix")
                    Spacer(modifier = modifier.width(4.dp))
                }
            }
        }
    }
}



@Composable
private fun LotterySmallTitle(modifier: Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 4.dp), horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Lotto 6/45", style = MaterialTheme.typography.bodyLarge)
    }
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
    ) {
        Text("번호를 추첨해주세요", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun LotteryTitle(modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "로또번호추첨기",
            style = MaterialTheme.typography.titleLarge,
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




