package com.example.jamjam.ui.mytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MytaskViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Task Fragment"
    }
    val text: LiveData<String> = _text
}
