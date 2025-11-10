package com.josecalvo.examenu2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josecalvo.examenu2.viewmodels.FormViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    viewModel: FormViewModel = hiltViewModel(),
    padding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var favoriteGame by remember { mutableStateOf("") }

    val lastUser by viewModel.lastUser.collectAsState()

    LaunchedEffect(lastUser) {
        lastUser?.let {
            name = it.name
            age = it.age.toString()
            favoriteGame = it.favoriteGame
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = favoriteGame,
            onValueChange = { favoriteGame = it },
            label = { Text("Videojuego Favorito") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val ageInt = age.toIntOrNull()
                if (name.isNotBlank() && ageInt != null && favoriteGame.isNotBlank()) {
                    viewModel.saveUser(name, ageInt, favoriteGame)
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Datos guardados exitosamente",
                            actionLabel = "OK",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        if (lastUser != null) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )
            Text(
                text = "Últimos datos guardados:",
                style = MaterialTheme.typography.titleMedium
            )
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nombre: ${lastUser?.name}")
                    Text("Edad: ${lastUser?.age}")
                    Text("Juego: ${lastUser?.favoriteGame}")
                }
            }
        }
    }
}