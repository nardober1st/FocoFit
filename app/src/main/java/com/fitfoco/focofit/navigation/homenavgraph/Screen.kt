package com.fitfoco.focofit.navigation.homenavgraph

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object IMC: Screen("imc")
    object Calories: Screen("caloria")
    object Objetivos: Screen("objetivos")
    object Alongamentos: Screen("alongamentos")
    object RotinasDiarias: Screen("rotinasDiarias")
    object Notificacoes: Screen("notificacoes")
}
