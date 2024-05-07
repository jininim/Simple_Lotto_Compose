package com.app.lottoappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.lottoappcompose.lottery.ShowLottoScreen
import com.app.lottoappcompose.ui.theme.LottoAppComposeTheme


@Composable
fun ScreenIntro(modifier : Modifier){

    var selectScreen by remember {
        mutableStateOf("")
    }


    val onBackPressed = {
        selectScreen = ""
    }

    if (selectScreen == ""){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            ShowTitleBanner(Modifier)
            ShowNavigationButton(Modifier,
                onLottoClick =  { selectScreen = "Lotto" },
                onPensionClick = {selectScreen = "Pension"},
                onWebViewClick = {selectScreen = "WebView"}
            )
        }
    }else if (selectScreen == "Lotto"){
        //로또 클릭 시
        ShowLottoScreen(modifier,onBackPressed)
    }

}
    @Composable
    fun ShowTitleBanner(modifier: Modifier){
        Row (horizontalArrangement = Arrangement.Center,modifier = modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.lottomain4), contentDescription = "banner",modifier = modifier
                .width(192.dp)
                .height(192.dp)
                .padding(top = 30.dp))
        }
    }

@Composable
fun ShowNavigationButton(modifier: Modifier, onLottoClick: () -> Unit,
                         onPensionClick: () -> Unit,onWebViewClick: () -> Unit){
    Column (
        modifier
            .fillMaxWidth()
            .padding(bottom = 177.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "종류를 선택 해주세요.")
        Spacer(modifier = modifier.height(30.dp))
        Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = onLottoClick ,modifier.width(140.dp)) {
                Text(text = "로또 6/45")
            }
            Button(onClick = onPensionClick,modifier.width(140.dp)) {
                Text(text = "연금복권 720+")
            }
        }
        Button(onClick = onWebViewClick,
            modifier
                .width(140.dp)
                .padding(top = 20.dp)) {
            Text(text = "로또 판매점")
        }
    }
}

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun GreetingPreview() {
        LottoAppComposeTheme {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                ScreenIntro(Modifier)
            }

        }
    }