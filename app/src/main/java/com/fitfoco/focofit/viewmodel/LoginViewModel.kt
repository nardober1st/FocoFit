package com.fitfoco.focofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) : ViewModel() {

    fun login(email: String, senha: String, listenerAuth: ListenerAuth) {
        viewModelScope.launch {
            repositoryAuth.login(email, senha, listenerAuth)
        }
    }
}