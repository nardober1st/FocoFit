package com.fitfoco.focofit.navigation.mainnavgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fitfoco.focofit.navigation.authnavgraph.authNavGraph
import com.fitfoco.focofit.navigation.homenavgraph.homeNavGraph
import com.fitfoco.focofit.navigation.rootnavgraph.RootGraphRoutes
import com.fitfoco.focofit.presentation.home.HomeScreen
import com.fitfoco.focofit.presentation.others.MenuScreen
import com.fitfoco.focofit.presentation.others.SettingsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RootGraphRoutes.MainGraphRoute.route,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Menu.route) {
            MenuScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        homeNavGraph(navController)
    }
}