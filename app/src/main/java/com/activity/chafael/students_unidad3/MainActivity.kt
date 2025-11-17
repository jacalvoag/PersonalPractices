package com.activity.chafael.students_unidad3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.activity.chafael.students_unidad3.data.AppDatabase
import com.activity.chafael.students_unidad3.data.StudentRepository
import com.activity.chafael.students_unidad3.navigation.AppNavigation
import com.activity.chafael.students_unidad3.viewmodel.StatisticsViewModel
import com.activity.chafael.students_unidad3.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = StudentRepository(database.studentDao())

        setContent {
            MaterialTheme {
                val studentViewModel = StudentViewModel(repository)
                val statisticsViewModel = StatisticsViewModel(repository)

                AppNavigation(
                    studentViewModel = studentViewModel,
                    statisticsViewModel = statisticsViewModel
                )
            }
        }
    }
}
