package com.fitfoco.focofit.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) : ViewModel() {

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    private var _mainChannelEvent = Channel<MainEvent>()
    var mainChannelEvent = _mainChannelEvent.receiveAsFlow()

    var mainState by mutableStateOf(MainState())
        private set

    fun userName(): Flow<String> {
        viewModelScope.launch {
            repositoryAuth.userName().collect {
                _name.value = it
            }
        }
        return name
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnSignOutClick -> {
                mainState = mainState.copy(
                    isSignOutClicked = true
                )
                onSignUserOut()
            }

            else -> {}
        }
    }

    private fun onSignUserOut() {
        viewModelScope.launch {
            repositoryAuth.signUserOut()
            _mainChannelEvent.send(MainEvent.OnSignOutClick)
        }
    }

    fun isUserSignedIn(): Boolean {
        return repositoryAuth.isUserSignedIn()
    }
}