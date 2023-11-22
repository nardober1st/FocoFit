package com.fitfoco.focofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.data.model.ExerciseFrequency
import com.fitfoco.focofit.data.model.Gender
import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.data.model.ObjetivosFit
import com.fitfoco.focofit.data.model.User
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.repository.RepositoryAuth
import com.fitfoco.focofit.repository.RepositoryObjetivo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjetivoViewModel @Inject constructor(
    private val repositoryObjetivo: RepositoryObjetivo
) : ViewModel() {

    var selectedGoal by mutableStateOf<ObjetivosFit>(ObjetivosFit.Ganhar)
        private set

    var selectedFrequency by mutableStateOf<ExerciseFrequency>(ExerciseFrequency.OnceAWeek)

    fun onFrequencyClick(frequency: ExerciseFrequency) {
        selectedFrequency = frequency
    }

    fun onObjetivoClick(goal: ObjetivosFit) {
        selectedGoal = goal
    }

    fun saveObjetivo(objetivo: Objetivo, listenerAuth: ListenerAuth) {
        viewModelScope.launch {
            repositoryObjetivo.saveObjetivo(objetivo, listenerAuth)
        }
    }
}