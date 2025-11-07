package com.josecalvo.examenu2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josecalvo.examenu2.data.database.UserDao
import com.josecalvo.examenu2.data.database.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    val lastUser: StateFlow<UserEntity?> = userDao.getLastUser()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveUser(name: String, age: Int, favoriteGame: String) {
        viewModelScope.launch {
            val user = UserEntity(
                name = name,
                age = age,
                favoriteGame = favoriteGame
            )
            userDao.insertUser(user)
        }
    }
}