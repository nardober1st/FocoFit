package com.fitfoco.focofit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.fitfoco.focofit.navigation.rootnavgraph.RootGraphRoutes
import com.fitfoco.focofit.navigation.rootnavgraph.RootNavigationGraph
import com.fitfoco.focofit.presentation.main.MainViewModel
import com.fitfoco.focofit.presentation.others.Splash
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // State to hold the start destination
            var startDestination by remember { mutableStateOf<String?>(null) }
            val mainViewModel: MainViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                mainViewModel.isUserSignedIn().collect { isSignedIn ->
                    startDestination = if (isSignedIn) {
                        // Define your main graph route here
                        RootGraphRoutes.MainGraphRoute.route
                    } else {
                        // Define your auth graph route here
                        RootGraphRoutes.AuthGraphRoute.route
                    }
                }
            }
            if (startDestination == null) {
                Splash() // Show a splash screen while determining the start destination
            } else {
                RootNavigationGraph(
                    navController = rememberNavController(),
                    startDestination = startDestination!!
                )
            }
        }
    }
}