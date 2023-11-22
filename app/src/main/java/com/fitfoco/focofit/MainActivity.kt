package com.fitfoco.focofit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitfoco.focofit.presentation.home.HomeScreen
import com.fitfoco.focofit.presentation.login.LoginScreen
import com.fitfoco.focofit.presentation.others.AlongamentosScreen
import com.fitfoco.focofit.presentation.others.Calories
import com.fitfoco.focofit.presentation.others.ForgotPassword
import com.fitfoco.focofit.presentation.others.IMC
import com.fitfoco.focofit.presentation.others.NotificacoesScreen
import com.fitfoco.focofit.presentation.objective.ObjetivosScreen
import com.fitfoco.focofit.presentation.others.RotinasDiariasScreen
import com.fitfoco.focofit.presentation.signup.SignupScreen
import com.fitfoco.focofit.viewmodel.SignupViewModel
import com.fitfoco.focofit.presentation.others.Splash
import com.fitfoco.focofit.viewmodel.HomeViewModel
import com.fitfoco.focofit.viewmodel.LoginViewModel
import com.fitfoco.focofit.viewmodel.ObjetivoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val signupViewModel: SignupViewModel = hiltViewModel()
            val loginViewModel: LoginViewModel = hiltViewModel()
            val homeViewModel: HomeViewModel = hiltViewModel()
            val objetivoViewModel: ObjetivoViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    Splash(navController)
                }
                composable("loginScreen") {
                    LoginScreen(navController, loginViewModel)
                }
                composable("signupScreen") {
                    SignupScreen(signupViewModel, navController)
                }
                composable("homeScreen") {
                    HomeScreen(navController, homeViewModel)
                }
                composable("imc") {
                    IMC()
                }
                composable("caloria") {
                    Calories()
                }
                composable("notificacoes") {
                    NotificacoesScreen()
                }
                composable("objetivos") {
                    ObjetivosScreen(objetivoViewModel, navController)
                }
                composable("alongamentos") {
                    AlongamentosScreen()
                }
                composable("rotinasDiarias") {
                    RotinasDiariasScreen()
                }
                composable("forgotPassword") {
                    ForgotPassword(navController, loginViewModel)
                }
            }
        }
    }
}