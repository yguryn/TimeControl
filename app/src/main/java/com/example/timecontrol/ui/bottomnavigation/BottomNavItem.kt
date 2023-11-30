package com.example.timecontrol.ui.bottomnavigation

import com.example.timecontrol.R

sealed class BottomNavItem(val title: String, val icon: Int, val screenRoute: String) {
    object SetTimeScreen : BottomNavItem("Time", R.drawable.baseline_home_24, "home")
    object StatsScreen : BottomNavItem("Stats", R.drawable.baseline_dashboard_24, "stats")
}
