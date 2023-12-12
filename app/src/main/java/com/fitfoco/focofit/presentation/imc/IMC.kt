package com.fitfoco.focofit.presentation.imc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitfoco.focofit.components.CardViewTable
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.Shape

@Composable
fun ImcScreen(
    imcViewModel: ImcViewModel = hiltViewModel()
) {

    val imc = imcViewModel.imcResult().collectAsState("").value
    val bmiCategory = imcViewModel.bmiCategory.collectAsState("").value
    val summaryText = imcViewModel.summaryText.collectAsState("").value

    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.Default.KeyboardArrowUp
    }

    Column(
        modifier = Modifier
            .background(BlueBackground)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Seu indice de massa corporal e:",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 40.dp, start = 20.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = BlueBackground),
            shape = Shape.small,
            border = BorderStroke(3.dp, Orange),
            modifier = Modifier
                .height(75.dp)
                .width(105.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = imc,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
            }
        }
        Text(
            text = bmiCategory,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )
        // Display the specific summary based on BMI category
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                summaryText,
                fontSize = 16.sp
            )
        }
        Row(
            Modifier
                .padding(start = 20.dp, top = 20.dp)
                .clickable {
                    expanded = !expanded
                }) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.padding(start = 6.dp))
            Text(
                text = "Ver tabela do IMC:",
                fontSize = 16.sp,
            )
        }
//        if (expanded) {
//            CardViewTable()
//        }
        // Use AnimatedVisibility to animate showing/hiding of  CardViewTable
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            CardViewTable()
        }
    }
}