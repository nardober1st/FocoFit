package com.fitfoco.focofit.repository

import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.datasource.DataSourceObjetivo
import com.fitfoco.focofit.listener.ListenerAuth
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryObjetivo @Inject constructor(
    private val dataSourceObjetivo: DataSourceObjetivo
) {

    fun saveObjetivo(objetivo: Objetivo, listenerAuth: ListenerAuth) {
        dataSourceObjetivo.saveObjetivos(objetivo, listenerAuth )
    }
}