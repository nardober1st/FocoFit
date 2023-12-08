package com.fitfoco.focofit.repository

import com.fitfoco.focofit.data.model.User
import com.fitfoco.focofit.datasource.DataSourceAuth
import com.fitfoco.focofit.listener.ListenerAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RepositoryAuth @Inject constructor(private val dataSourceAuth: DataSourceAuth) {

    fun signup(
        user: User,
        senha: String,
        listenerAuth: ListenerAuth
    ) {
        dataSourceAuth.signup(user, senha, listenerAuth)
    }

    fun login(
        email: String,
        senha: String,
        listenerAuth: ListenerAuth
    ) {
        dataSourceAuth.login(email, senha, listenerAuth)
    }

    fun forgotPassword(
        email: String,
        listenerAuth: ListenerAuth
    ) {
        dataSourceAuth.forgotPassword(email, listenerAuth)
    }

    fun isUserSignedIn(): Flow<Boolean> {
        return dataSourceAuth.isUserSignedIn()
    }

    fun userName(): Flow<String>{
        return dataSourceAuth.userName()
    }

    fun signUserOut() {
        dataSourceAuth.signUserOut()
    }
}