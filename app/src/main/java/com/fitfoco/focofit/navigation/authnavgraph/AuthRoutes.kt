package com.fitfoco.focofit.navigation.authnavgraph

sealed class AuthRoutes(val route: String) {
    object LoginRoute : AuthRoutes(route = "login")
    object SignupRoute : AuthRoutes(route = "signup")
    object ForgotPassword : AuthRoutes(route = "forgot")
}