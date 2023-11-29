package com.fitfoco.focofit.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String
) {

    object SplashScreen : Route(route = "splash")
    object LoginScreen : Route(route = "loginScreen")
    object SignupScreen : Route(route = "signupScreen")
    object HomeScreen : Route(route = "homeScreen")
    object ImcScreen : Route(route = "imc")
    object CaloriaScreen : Route(route = "caloria")
    object NotificacoesScreen : Route(route = "notificacoes")
    object ObjetivosScreen : Route(route = "objetivos")
    object AlongamentosScreen : Route(route = "alongamentos")
    object RotinasDiariasScreen : Route(route = "rotinasDiarias")
    object ForgotPasswordScreen : Route(route = "forgotPassword")
    object NewsScreen : Route(route = "newsScreen")
    object GraphScreen : Route(route = "graphScreen")
}
