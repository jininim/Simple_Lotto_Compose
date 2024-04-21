package com.app.composelottoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.composelottoapp.ui.theme.ComposeLottoAppTheme
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLottoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "intro") {
                    composable("intro") {
                        IntroScreen(navController)
                    }
                    composable("lotto") {
                        LottoScreen(navController)
                    }
                    composable("pension") {
//                        PensionScreen(navController)
                    }
                }


            }
        }
    }
}

@Composable
fun IntroScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.3f)
                .padding(top = 20.dp),
            painter = painterResource(
                id = R.drawable.banner
            ),
            contentDescription = "banner",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "종류를 선택해주세요", fontSize = 24.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                navController.navigate("lotto")
            }, modifier = Modifier.width(150.dp)) {
                Text(text = "로또6/45")
            }
            Button(
                onClick = { navController.navigate("pention") },
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "연금복권720+")
            }
        }
        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(150.dp)) {
            Text(text = "로또 판매점")
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LottoScreen(navController: NavController) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("번호 추첨", "번호 통계")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        if (state == 0) {
            Text("Lotto 6/45", fontSize = 20.sp, fontWeight = FontWeight.W600)



        }
    }
}

@Composable
fun DashLine() {
    Image(
        painter = painterResource(id = R.drawable.dash_line),
        contentDescription = "dash_line",
        modifier = Modifier
            .fillMaxWidth()
            .height(1.5.dp)
    )
}

@Composable
fun LotteryScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lotto 6/45", fontSize = 20.sp, fontWeight = FontWeight.W600)
        DashLine()
        Text("번호를 추첨해주세요 !", fontSize = 20.sp, fontWeight = FontWeight.W600)
    }
}

@Composable
fun PensionScreen(navController: NavController) {

}

@Composable
fun WepViewScreen(navController: NavController) {
}

