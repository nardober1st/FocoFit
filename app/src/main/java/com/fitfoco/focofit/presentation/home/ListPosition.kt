package com.fitfoco.focofit.presentation.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fitfoco.focofit.ui.theme.Blue03
import com.fitfoco.focofit.ui.theme.Blue04
import com.fitfoco.focofit.util.Screen

@Composable
fun ListPosition(
    list: MutableList<ListItem>,
    position: Int,
    context: Context,
    navController: NavController
) {

    val icon = list[position].icon
    val text = list[position].title

    Column(
        modifier = Modifier
            .clickable {
                when (position) {
                    0 -> navController.navigate(Screen.IMC.route)
                    1 -> navController.navigate(Screen.Calories.route)
                    2 -> navController.navigate(Screen.Objetivos.route)
                    3 -> navController.navigate(Screen.Alongamentos.route)
                    4 -> navController.navigate(Screen.RotinasDiarias.route)
                    5 -> navController.navigate(Screen.Notificacoes.route)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = icon,
                contentDescription = null,
                tint = Blue04
            )
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Blue04
            )
        }
        Row(
            modifier = Modifier
                .background(Blue04)
                .fillMaxWidth()
                .height(1.dp)
        ) {

        }
    }
}