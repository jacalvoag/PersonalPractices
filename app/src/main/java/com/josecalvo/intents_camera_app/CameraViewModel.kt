package com.josecalvo.intents_camera_app

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraViewModel : ViewModel(){

    private val _photoBitmap = MutableStateFlow<Bitmap?>(null)
    val photoBitmap = _photoBitmap.asStateFlow()

    fun onPictureTaken(image: Bitmap){
        _photoBitmap.value = image
    }
}