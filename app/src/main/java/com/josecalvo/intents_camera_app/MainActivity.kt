package com.josecalvo.intents_camera_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.josecalvo.intents_camera_app.ui.theme.Intents_camera_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intents_camera_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {}
            }
        }
    }
}
