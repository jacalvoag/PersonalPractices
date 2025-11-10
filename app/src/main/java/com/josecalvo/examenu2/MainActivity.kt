package com.josecalvo.examenu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.josecalvo.examenu2.screens.DashboardScreen
import com.josecalvo.examenu2.screens.FormScreen
import com.josecalvo.examenu2.screens.ThemeScreen
import com.josecalvo.examenu2.ui.theme.Examenu2Theme
import com.josecalvo.examenu2.viewmodels.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            Examenu2Theme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val snackbarHostState = remember { SnackbarHostState() }

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    val titles = mapOf(
                        "dashboard" to "Dashboard Principal",
                        "theme" to "Configuración de Tema",
                        "form" to "Formulario de Usuario"
                    )
                    val currentTitle = titles[currentRoute] ?: "Dashboard"

                    val isDashboard = currentRoute == "dashboard"

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                Spacer(Modifier.height(12.dp))
                                NavigationDrawerItem(
                                    icon = { Icon(Icons.Default.DarkMode, contentDescription = null) },
                                    label = { Text("Configurar Tema") },
                                    selected = currentRoute == "theme",
                                    onClick = {
                                        navController.navigate("theme")
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                NavigationDrawerItem(
                                    icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                                    label = { Text("Llenar Formulario") },
                                    selected = currentRoute == "form",
                                    onClick = {
                                        navController.navigate("form")
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    ) {
                        Scaffold(
                            snackbarHost = { SnackbarHost(snackbarHostState) },
                            topBar = {
                                TopAppBar(
                                    title = { Text(currentTitle) },
                                    navigationIcon = {
                                        if (isDashboard) {
                                            IconButton(onClick = {
                                                scope.launch { drawerState.open() }
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Default.Menu,
                                                    contentDescription = "Abrir menú"
                                                )
                                            }
                                        } else {
                                            IconButton(onClick = { navController.popBackStack() }) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = "Volver"
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        ) { padding ->
                            NavHost(navController = navController, startDestination = "dashboard") {
                                composable("dashboard") {
                                    DashboardScreen(
                                        padding = padding
                                    )
                                }
                                composable("theme") {
                                    ThemeScreen(
                                        viewModel = themeViewModel,
                                        padding = padding
                                    )
                                }
                                composable("form") {
                                    FormScreen(
                                        padding = padding,
                                        snackbarHostState = snackbarHostState,
                                        scope = scope
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}