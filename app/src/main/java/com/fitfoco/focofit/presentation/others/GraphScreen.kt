package com.fitfoco.focofit.presentation.others

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.fitfoco.focofit.components.BottomBarComponent
import com.fitfoco.focofit.navigation.AppNavigator

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GraphScreen(navController: NavController) {


    Scaffold(
        bottomBar = {
            AppNavigator()
        }
    ) {
        Text(text = "graph")
    }
}