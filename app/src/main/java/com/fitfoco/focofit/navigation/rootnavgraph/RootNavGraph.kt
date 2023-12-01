package com.fitfoco.focofit.navigation.rootnavgraph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fitfoco.focofit.navigation.authnavgraph.authNavGraph
import com.fitfoco.focofit.presentation.main.MainScreen
import com.fitfoco.focofit.presentation.main.MainEvent
import com.fitfoco.focofit.presentation.main.MainViewModel
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {

    val mainViewModel: MainViewModel = hiltViewModel()

    val isUserSignedIn = mainViewModel.isUserSignedIn()

    // Listen for changes in sign-out status using LaunchedEffect
    LaunchedEffect(isUserSignedIn) {
        mainViewModel.mainChannelEvent.collect { event ->
            when (event) {
                is MainEvent.OnSignOutClick -> {
                    // Update the navigation to AuthGraph after sign-out event
                    navController.navigate(RootGraphRoutes.AuthGraphRoute.route) {
                        // Clear back stack so pressing back won't return to MainGraph
                        Log.d("TAGY", "User: ${FirebaseAuth.getInstance().currentUser}")
                        popUpTo(RootGraphRoutes.MainGraphRoute.route) {
                            inclusive = true
                        }
                    }
                }

                else -> {}
            }
        }
    }

    NavHost(
        navController = navController,
        route = RootGraphRoutes.RootGraphRoute.route,
        startDestination = if (isUserSignedIn) RootGraphRoutes.MainGraphRoute.route else RootGraphRoutes.AuthGraphRoute.route
    ) {
        authNavGraph(navController)
        composable(route = RootGraphRoutes.MainGraphRoute.route) {
            MainScreen(
                onEvent = mainViewModel::onEvent
            )
        }
    }
}