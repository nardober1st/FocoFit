package com.fitfoco.focofit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.Shape

@Composable
fun CardView(
    modifier: Modifier
){

    Card (
        colors = CardDefaults.cardColors(containerColor = BlueBackground),
        shape = Shape.small,
        border = BorderStroke(3.dp, Orange),
        modifier = modifier.height(50.dp).width(95.dp).padding(start = 2.dp)
    ){

        Text(text = "1750", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Black)

    }
}
