package com.fitfoco.focofit.presentation.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fitfoco.focofit.data.model.Gender

class SignupViewModel() : ViewModel() {

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }
}