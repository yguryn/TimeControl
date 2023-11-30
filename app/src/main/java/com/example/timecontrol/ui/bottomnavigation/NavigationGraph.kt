package com.example.timecontrol.ui.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.timecontrol.ui.statsscreen.StatsScreen
import com.example.timecontrol.ui.timescreen.TimeScreen

@Composable
fun NavigationGraph(navController: NavHostController, isAppOpenedFromNotification: Boolean) {
    NavHost(navController, startDestination = BottomNavItem.SetTimeScreen.screenRoute) {
        composable(BottomNavItem.SetTimeScreen.screenRoute) {
            TimeScreen(isAppOpenedFromNotification)
        }
        composable(BottomNavItem.StatsScreen.screenRoute) {
            StatsScreen()
        }
    }
}
