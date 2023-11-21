package com.fitfoco.focofit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.Shape

@Composable
fun CardView(
    modifier: Modifier,
    text: String
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = BlueBackground),
        shape = Shape.small,
        border = BorderStroke(3.dp, Orange),
        modifier = modifier
            .height(75.dp)
            .width(105.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (txt) = createRefs()

            Text(
                text = text,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier.constrainAs(txt) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}