package com.activity.chafael.students_unidad3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.activity.chafael.students_unidad3.data.Student
import com.activity.chafael.students_unidad3.data.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    init {
        loadStudents()
    }

    private fun loadStudents() {
        viewModelScope.launch {
            repository.allStudents.collect { studentList ->
                _students.value = studentList
            }
        }
    }

    fun addStudent(firstName: String, lastName: String, grade: String, group: String, score: Double) {
        viewModelScope.launch {
            val student = Student(
                firstName = firstName,
                lastName = lastName,
                grade = grade,
                group = group,
                score = score
            )
            repository.insert(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.delete(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.update(student)
        }
    }
}

class StudentViewModelFactory(private val repository: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}