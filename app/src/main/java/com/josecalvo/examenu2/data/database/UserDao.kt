package com.josecalvo.examenu2.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_data ORDER BY id DESC LIMIT 1")
    fun getLastUser(): Flow<UserEntity?>

    @Query("SELECT * FROM user_data")
    fun getAllUsers(): Flow<List<UserEntity>>
}