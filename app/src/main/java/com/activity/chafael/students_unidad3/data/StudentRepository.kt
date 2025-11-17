package com.activity.chafael.students_unidad3.data

import kotlinx.coroutines.flow.Flow

class StudentRepository(private val dao: StudentDao) {
    // Tab 1
    val allStudents: Flow<List<Student>> = dao.getAllStudents()
    suspend fun insert(student: Student) = dao.insert(student)
    suspend fun delete(student: Student) = dao.delete(student)

    // Tab 2
    suspend fun getAverageScore(): Double = dao.getAverageScore() ?: 0.0
    suspend fun getStudentWithLowestScore(): Student? = dao.getStudentWithLowestScore()
    suspend fun getTop3ByGroup(group: String): List<Student> = dao.getTop3ByGroup(group)
    suspend fun getAllGroups(): List<String> = dao.getAllGroups()
}
