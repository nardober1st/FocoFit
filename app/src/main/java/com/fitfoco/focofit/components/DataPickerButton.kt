package com.fitfoco.focofit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fitfoco.focofit.ui.theme.Orange

@Composable
fun DataPickerButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(start = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 2.dp,
                color = Orange,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            }
            .padding(10.dp),
    ) {
        Text(
            text = text,
            color = Color.Black,
        )
    }
}