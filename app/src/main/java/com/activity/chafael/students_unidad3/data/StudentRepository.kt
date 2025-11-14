package com.activity.chafael.students_unidad3.data

import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }

    suspend fun update(student: Student) {
        studentDao.update(student)
    }

    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }

    suspend fun getStudentById(id: Int): Student? {
        return studentDao.getStudentById(id)
    }
}
