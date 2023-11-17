package com.fitfoco.focofit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.White

@Composable
fun ButtonEdit(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
) {

    Button(
        onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange
        ),
        border = BorderStroke(1.dp, White)
    ) {
        Text(
            text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
    }
}
