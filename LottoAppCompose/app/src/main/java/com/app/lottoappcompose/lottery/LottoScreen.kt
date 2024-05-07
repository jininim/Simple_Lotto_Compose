package com.app.lottoappcompose.lottery

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.lottoappcompose.R


sealed class Screen(val route: String, val title : String ,@StringRes val resourceId: Int) {
    @SuppressLint("ResourceType")
    data object Lottery : Screen("screen1","번호 추첨", R.drawable.lotto)
    @SuppressLint("ResourceType")
    data object Statistics : Screen("screen2","번호 통계", R.drawable.piechart)
}

@Composable
fun ShowLottoScreen(modifier: Modifier, onBackPressed: () -> Unit) {

    val navController = rememberNavController()

    BackHandler {
        // Navigate back to ScreenIntro when back button is pressed
        onBackPressed()
    }

    Scaffold(
        bottomBar = {
            MyBottomNavigationBar(navController,modifier)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "screen1", Modifier.padding(innerPadding)) {
            composable("screen1") { LotteryScreen(modifier) }
            composable("screen2") { LotteryStatisticsScreen() }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun MyBottomNavigationBar(navController: NavHostController,modifier: Modifier) {
    BottomNavigation(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        backgroundColor = Color.LightGray,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        val items = listOf(
           Screen.Lottery,
            Screen.Statistics
        )
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(modifier = modifier.height(24.dp),painter = painterResource(id = item.resourceId), contentDescription = null, tint = Color.Unspecified)},
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true


                        restoreState = true
                    }
                },
                modifier = modifier.padding(top = 12.dp, bottom = 16.dp),
                selectedContentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}




















