package com.fitfoco.focofit.util

sealed class Screen(val route: String) {
    object IMC: Screen("imc")
    object Calories: Screen("caloria")
    object Objetivos: Screen("objetivos")
    object Alongamentos: Screen("alongamentos")
    object RotinasDiarias: Screen("rotinasDiarias")
    object Notificacoes: Screen("notificacoes")
}
