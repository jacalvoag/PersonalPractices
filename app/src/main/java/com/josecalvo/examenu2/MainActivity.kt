package com.josecalvo.examenu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josecalvo.examenu2.screens.DashboardScreen
import com.josecalvo.examenu2.screens.FormScreen
import com.josecalvo.examenu2.screens.ThemeScreen
import com.josecalvo.examenu2.ui.theme.Examenu2Theme
import com.josecalvo.examenu2.viewmodels.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            Examenu2Theme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "dashboard") {
                        composable("dashboard") {
                            DashboardScreen(
                                onNavigateToTheme = { navController.navigate("theme") },
                                onNavigateToForm = { navController.navigate("form") }
                            )
                        }
                        composable("theme") {
                            ThemeScreen(
                                viewModel = themeViewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable("form") {
                            FormScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}