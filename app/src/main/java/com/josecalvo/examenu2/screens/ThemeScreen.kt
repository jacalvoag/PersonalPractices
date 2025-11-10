package com.josecalvo.examenu2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.josecalvo.examenu2.viewmodels.ThemeViewModel

@Composable
fun ThemeScreen(
    viewModel: ThemeViewModel,
    padding: PaddingValues
) {
    val isDarkMode by viewModel.isDarkMode.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Modo Oscuro",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (isDarkMode) "Activado" else "Desactivado",
                style = MaterialTheme.typography.bodyLarge
            )

            Switch(
                checked = isDarkMode,
                onCheckedChange = { viewModel.toggleTheme(it) }
            )
        }
    }
}