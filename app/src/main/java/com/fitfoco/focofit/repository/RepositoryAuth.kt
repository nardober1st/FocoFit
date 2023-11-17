package com.fitfoco.focofit.repository

import com.fitfoco.focofit.datasource.DataSourceAuth
import com.fitfoco.focofit.listener.ListenerAuth
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryAuth @Inject constructor(private val dataSourceAuth: DataSourceAuth) {

    fun signup(
        email: String,
        senha: String,
        apelido: String,
        nome: String,
        listenerAuth: ListenerAuth
    ) {
        dataSourceAuth.signup(email, senha, apelido, nome, listenerAuth)
    }
}