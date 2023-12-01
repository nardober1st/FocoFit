package com.fitfoco.focofit.navigation.homenavgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitfoco.focofit.navigation.mainnavgraph.BottomBarScreen
import com.fitfoco.focofit.navigation.rootnavgraph.RootGraphRoutes
import com.fitfoco.focofit.presentation.objective.ObjetivosScreen
import com.fitfoco.focofit.presentation.others.AlongamentosScreen
import com.fitfoco.focofit.presentation.others.Calories
import com.fitfoco.focofit.presentation.others.IMC
import com.fitfoco.focofit.presentation.others.NotificacoesScreen
import com.fitfoco.focofit.presentation.others.RotinasDiariasScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = RootGraphRoutes.HomeGraphRoute.route,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = Screen.IMC.route) {
            IMC()
        }
        composable(route = Screen.Calories.route) {
            Calories()
        }
        composable(route = Screen.Objetivos.route) {
            ObjetivosScreen(navController = navController)
        }
        composable(route = Screen.Alongamentos.route) {
            AlongamentosScreen()
        }
        composable(route = Screen.RotinasDiarias.route) {
            RotinasDiariasScreen()
        }
        composable(route = Screen.Notificacoes.route) {
            NotificacoesScreen()
        }
    }
}