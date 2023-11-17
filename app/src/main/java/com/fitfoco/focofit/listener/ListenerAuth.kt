package com.fitfoco.focofit.listener

interface ListenerAuth {

    fun onSuccess(message: String, screen: String)
    fun onFailure(error: String)
}