package com.example.timecontrol.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.timecontrol.ui.bottomnavigation.AppBottomNavigation
import com.example.timecontrol.ui.bottomnavigation.NavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenView(openedFromNotification: Boolean){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController, isAppOpenedFromNotification = openedFromNotification)
    }
}