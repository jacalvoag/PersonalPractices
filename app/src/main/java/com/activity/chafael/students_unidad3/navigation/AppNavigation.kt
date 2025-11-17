package com.activity.chafael.students_unidad3.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.activity.chafael.students_unidad3.ui.screens.StatisticsScreen
import com.activity.chafael.students_unidad3.ui.screens.StudentManagementScreen
import com.activity.chafael.students_unidad3.viewmodel.StatisticsViewModel
import com.activity.chafael.students_unidad3.viewmodel.StudentViewModel

sealed class Tab(val title: String, val icon: ImageVector) {
    object Students : Tab("Estudiantes", Icons.Default.Person)
    object Statistics : Tab("Estadisticas", Icons.Default.ShowChart)
}

@Composable
fun AppNavigation(
    studentViewModel: StudentViewModel,
    statisticsViewModel: StatisticsViewModel
) {
    var selectedTab by remember { mutableStateOf<Tab>(Tab.Students) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab is Tab.Students,
                    onClick = { selectedTab = Tab.Students },
                    icon = { Icon(Tab.Students.icon, null) },
                    label = { Text(Tab.Students.title) }
                )
                NavigationBarItem(
                    selected = selectedTab is Tab.Statistics,
                    onClick = { selectedTab = Tab.Statistics },
                    icon = { Icon(Tab.Statistics.icon, null) },
                    label = { Text(Tab.Statistics.title) }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            is Tab.Students -> StudentManagementScreen(studentViewModel)
            is Tab.Statistics -> StatisticsScreen(statisticsViewModel)
        }
    }
}