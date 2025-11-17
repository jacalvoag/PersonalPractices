package com.activity.chafael.students_unidad3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.activity.chafael.students_unidad3.ui.components.AddStudentForm
import com.activity.chafael.students_unidad3.ui.components.StudentList
import com.activity.chafael.students_unidad3.viewmodel.StudentViewModel

enum class StudentView { LIST, ADD }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentManagementScreen(viewModel: StudentViewModel) {
    var currentView by remember { mutableStateOf(StudentView.LIST) }
    val students by viewModel.students.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Gestión de estudiantes") }) }
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
                    onClick = { currentView = StudentView.LIST },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Lista")
                }
                Button(
                    onClick = { currentView = StudentView.ADD },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Añadir")
                }
            }

            Divider()

            when (currentView) {
                StudentView.LIST -> StudentList(
                    students = students,
                    onDelete = { viewModel.deleteStudent(it) }
                )
                StudentView.ADD -> AddStudentForm(
                    onAdd = { first, last, grade, group, score ->
                        viewModel.addStudent(first, last, grade, group, score)
                    },
                    onSuccess = { currentView = StudentView.LIST }
                )
            }
        }
    }
}