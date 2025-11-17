package com.activity.chafael.students_unidad3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activity.chafael.students_unidad3.data.Student
import com.activity.chafael.students_unidad3.data.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    val students = repository.allStudents.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addStudent(firstName: String, lastName: String, grade: String, group: String, score: Double) {
        viewModelScope.launch {
            repository.insert(Student(0, firstName, lastName, grade, group, score))
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.delete(student)
        }
    }
}