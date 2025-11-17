package com.activity.chafael.students_unidad3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activity.chafael.students_unidad3.data.Student
import com.activity.chafael.students_unidad3.data.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(private val repository: StudentRepository) : ViewModel() {

    private val _averageScore = MutableStateFlow(0.0)
    val averageScore: StateFlow<Double> = _averageScore

    private val _studentWithLag = MutableStateFlow<Student?>(null)
    val studentWithLag: StateFlow<Student?> = _studentWithLag

    private val _top3Students = MutableStateFlow<List<Student>>(emptyList())
    val top3Students: StateFlow<List<Student>> = _top3Students

    private val _groups = MutableStateFlow<List<String>>(emptyList())
    val groups: StateFlow<List<String>> = _groups

    init {
        loadGroups()
    }

    fun loadAverageScore() {
        viewModelScope.launch {
            _averageScore.value = repository.getAverageScore()
        }
    }

    fun loadStudentWithLag() {
        viewModelScope.launch {
            _studentWithLag.value = repository.getStudentWithLowestScore()
        }
    }

    fun loadTop3ByGroup(group: String) {
        viewModelScope.launch {
            _top3Students.value = repository.getTop3ByGroup(group)
        }
    }

    private fun loadGroups() {
        viewModelScope.launch {
            _groups.value = repository.getAllGroups()
        }
    }
}
