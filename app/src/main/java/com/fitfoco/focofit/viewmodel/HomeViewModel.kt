package com.fitfoco.focofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositoryAuth: RepositoryAuth): ViewModel() {

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    fun userName(): Flow<String>{
        viewModelScope.launch {
            repositoryAuth.userName().collect{
                _name.value = it
            }
        }
        return name
    }
}