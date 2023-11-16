package com.fitfoco.focofit.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fitfoco.focofit.R
import com.fitfoco.focofit.ui.theme.Blue03
import com.fitfoco.focofit.ui.theme.BlueBackground
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navController: NavController
){
    LaunchedEffect(key1 = true){
        delay(2500)
        navController.navigate("loginScreen")
    }
    SplashScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(){

    Scaffold(
        modifier = Modifier.background(BlueBackground),
        containerColor = Color.Transparent
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.group_3),
                contentDescription = "")

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Blue03,
                modifier = Modifier.padding(top = 25.dp)
            )
            Circular()
        }
    }

}

@Composable
fun Circular(){
    val progress = 0.99f
    val infinite = rememberInfiniteTransition(label = "")

    val animation by infinite.animateFloat(
        initialValue = 0.0f,
        targetValue = progress,
        animationSpec = infiniteRepeatable(animation = tween(900)), label = "")

    CircularProgressIndicator(
        progress = animation,
        color = Blue03,
        modifier = Modifier
            .padding(top = 15.dp)
            .size(35.dp),
        strokeWidth = 5.dp
    )
}