package com.example.counter

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Counter {
    var count by mutableIntStateOf(0)
        private set

    fun increase() {
        count++
    }
}