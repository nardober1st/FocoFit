package com.fitfoco.focofit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitfoco.focofit.presentation.login.LoginScreen
import com.fitfoco.focofit.presentation.signup.SignupScreen
import com.fitfoco.focofit.presentation.signup.SignupViewModel
import com.fitfoco.focofit.presentation.others.Splash

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModel: SignupViewModel = viewModel()

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    Splash(navController)
                }
                composable("loginScreen") {
                    LoginScreen(navController)
                }
                composable("signupScreen") {
                    SignupScreen(viewModel)
                }
            }
        }
    }
}