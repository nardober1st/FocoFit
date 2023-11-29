package com.fitfoco.focofit.presentation.others

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.fitfoco.focofit.components.BottomBarComponent
import com.fitfoco.focofit.navigation.AppNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News(navController: NavController) {
    Scaffold(
        bottomBar = {
            AppNavigator()
        }
    ) {
        Text(text = "new")
    }
}