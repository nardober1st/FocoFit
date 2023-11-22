package com.fitfoco.focofit.data.model

sealed class ObjetivosFit(val objetivo: String) {
    object Manter : ObjetivosFit("manter")
    object Ganhar : ObjetivosFit("ganhar")
    object Emagrecer : ObjetivosFit("emagrecer")

    companion object {
        fun fromString(name: String): ObjetivosFit {
            return when (name) {
                "manter" -> Manter
                "ganhar" -> Ganhar
                "emagrecer" -> Emagrecer
                else -> Ganhar
            }
        }
    }
}