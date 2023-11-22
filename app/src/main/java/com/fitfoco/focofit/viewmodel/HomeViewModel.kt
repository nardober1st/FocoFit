package com.fitfoco.focofit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.repository.RepositoryAuth
import com.fitfoco.focofit.repository.RepositoryObjetivo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryAuth: RepositoryAuth,
    private val repositoryObjetivo: RepositoryObjetivo
) : ViewModel() {

    private val _todosObjetivos = MutableStateFlow<MutableList<Objetivo>>(mutableListOf())
    val todosObjetivos: StateFlow<MutableList<Objetivo>> = _todosObjetivos

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    private val _peso = MutableStateFlow("")
    private val peso: StateFlow<String> = _peso

    private val _altura = MutableStateFlow("")
    private val altura: StateFlow<String> = _altura

    fun userName(): Flow<String> {
        viewModelScope.launch {
            repositoryAuth.userName().collect {
                _name.value = it
            }
        }
        return name
    }

//    fun getObjetivos(): Flow<MutableList<Objetivo>> {
//        viewModelScope.launch {
//            repositoryObjetivo.getObjetivos().collect {
//                _todosObjetivos.value = it
//                Log.d("TAGY", it.toString())
//            }
//        }
//        return todosObjetivos
//    }

    fun pesoUser(): Flow<String> {
        viewModelScope.launch {
            repositoryObjetivo.pesoUser().collect {
                _peso.value = it
            }
        }
        return peso
    }

    fun alturaUser(): Flow<String> {
        viewModelScope.launch {
            repositoryObjetivo.alturaUser().collect {
                _altura.value = it
            }
        }
        return altura
    }
}