package com.activity.chafael.students_unidad3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.activity.chafael.students_unidad3.data.Student

@Composable
fun StudentList(
    students: List<Student>,
    onDelete: (Student) -> Unit
) {
    if (students.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Aún no hay estudiantes. ¡Añade uno para empezar!")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(students) { student ->
                StudentCard(student = student, onDelete = { onDelete(student) })
            }
        }
    }
}

@Composable
fun StudentCard(
    student: Student,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${student.firstName} ${student.lastName}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Grado: ${student.grade} | Grupo: ${student.group}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Puntaje: ${student.score}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AddStudentForm(
    onAdd: (String, String, String, String, Double) -> Unit,
    onSuccess: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Add New Student", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && firstName.isBlank()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && lastName.isBlank()
        )

        OutlinedTextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text("Grado") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && grade.isBlank()
        )

        OutlinedTextField(
            value = group,
            onValueChange = { group = it },
            label = { Text("Grupo") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && group.isBlank()
        )

        OutlinedTextField(
            value = score,
            onValueChange = { score = it },
            label = { Text("Puntaje") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && score.toDoubleOrNull() == null
        )

        if (showError) {
            Text(
                "Por favor, rellene todos los campos correctamente",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick = {
                if (firstName.isNotBlank() && lastName.isNotBlank() &&
                    grade.isNotBlank() && group.isNotBlank() &&
                    score.toDoubleOrNull() != null) {
                    onAdd(firstName, lastName, grade, group, score.toDouble())
                    firstName = ""
                    lastName = ""
                    grade = ""
                    group = ""
                    score = ""
                    showError = false
                    onSuccess()
                } else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir estudiante")
        }
    }
}