package com.fitfoco.focofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.data.model.Gender
import com.fitfoco.focofit.data.model.User
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) : ViewModel() {

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    fun signup(
        user: User,
        senha: String,
        listenerAuth: ListenerAuth
    ) {
        viewModelScope.launch {
            repositoryAuth.signup(user, senha, listenerAuth)
        }
    }
}