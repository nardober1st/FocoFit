package com.fitfoco.focofit.navigation.authnavgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitfoco.focofit.navigation.rootnavgraph.RootGraphRoutes
import com.fitfoco.focofit.presentation.login.LoginScreen
import com.fitfoco.focofit.presentation.others.ForgotPassword
import com.fitfoco.focofit.presentation.signup.SignupScreen
import com.fitfoco.focofit.presentation.login.LoginViewModel
import com.fitfoco.focofit.presentation.signup.SignupViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = RootGraphRoutes.AuthGraphRoute.route,
        startDestination = AuthRoutes.LoginRoute.route
    ) {
        composable(route = AuthRoutes.LoginRoute.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                navController,
                loginViewModel
            )
        }
        composable(route = AuthRoutes.SignupRoute.route) {
            val signupViewModel: SignupViewModel = hiltViewModel()

            SignupScreen(
                signupViewModel,
                navController,
            )
        }
        composable(route = AuthRoutes.ForgotPassword.route) {
            ForgotPassword(navController = navController)
        }
    }
}