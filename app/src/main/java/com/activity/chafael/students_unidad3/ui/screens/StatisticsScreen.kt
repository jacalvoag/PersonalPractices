package com.activity.chafael.students_unidad3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.activity.chafael.students_unidad3.ui.components.AverageCard
import com.activity.chafael.students_unidad3.ui.components.LagStudentCard
import com.activity.chafael.students_unidad3.ui.components.Top3List
import com.activity.chafael.students_unidad3.viewmodel.StatisticsViewModel

enum class StatsView { AVERAGE, LAG, TOP3 }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(viewModel: StatisticsViewModel) {
    var currentView by remember { mutableStateOf(StatsView.AVERAGE) }
    val average by viewModel.averageScore.collectAsState()
    val lagStudent by viewModel.studentWithLag.collectAsState()
    val top3 by viewModel.top3Students.collectAsState()
    val groups by viewModel.groups.collectAsState()

    LaunchedEffect(currentView) {
        when (currentView) {
            StatsView.AVERAGE -> viewModel.loadAverageScore()
            StatsView.LAG -> viewModel.loadStudentWithLag()
            StatsView.TOP3 -> {}
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Estadística") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { currentView = StatsView.AVERAGE },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Promedio")
                }
                Button(
                    onClick = { currentView = StatsView.LAG },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Rezago")
                }
                Button(
                    onClick = { currentView = StatsView.TOP3 },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Top 3")
                }
            }

            Divider()

            when (currentView) {
                StatsView.AVERAGE -> AverageCard(average)
                StatsView.LAG -> LagStudentCard(lagStudent)
                StatsView.TOP3 -> Top3List(
                    students = top3,
                    groups = groups,
                    onGroupSelected = { viewModel.loadTop3ByGroup(it) }
                )
            }
        }
    }
}