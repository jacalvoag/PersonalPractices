package com.activity.chafael.students_unidad3.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    // Tab 1 - CRUD
    @Query("SELECT * FROM students ORDER BY lastName ASC")
    fun getAllStudents(): Flow<List<Student>>

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    // Tab 2 - Statistics
    @Query("SELECT AVG(score) FROM students")
    suspend fun getAverageScore(): Double?

    @Query("SELECT * FROM students ORDER BY score ASC LIMIT 1")
    suspend fun getStudentWithLowestScore(): Student?

    @Query("SELECT * FROM students WHERE `group` = :group ORDER BY score DESC LIMIT 3")
    suspend fun getTop3ByGroup(group: String): List<Student>

    @Query("SELECT DISTINCT `group` FROM students ORDER BY `group` ASC")
    suspend fun getAllGroups(): List<String>
}