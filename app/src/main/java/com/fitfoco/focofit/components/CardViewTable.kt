package com.fitfoco.focofit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitfoco.focofit.R
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.Shape

@Composable
fun CardViewTable() {

    Card(
        colors = CardDefaults.cardColors(containerColor = BlueBackground),
        shape = Shape.small,
        border = BorderStroke(3.dp, Orange),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val textModifier =
                Modifier.padding(horizontal = 5.dp, vertical = 3.dp) // Padding for individual texts

            val (below, normal, overweight, obesity1, obesity2, obesity3, first, second, third, fourth, fifth, sixth) = createRefs()

            Text(
                text = stringResource(id = R.string.abaixo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(below) {
                    top.linkTo(parent.top, 5.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.normal),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(normal) {
                    top.linkTo(below.bottom, 3.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.sobre),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(overweight) {
                    top.linkTo(normal.bottom, 3.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.obs1),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(obesity1) {
                    top.linkTo(overweight.bottom, 3.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.obs2),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(obesity2) {
                    top.linkTo(obesity1.bottom, 3.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.obs3),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier
                    .padding(bottom = 3.dp)
                    .constrainAs(obesity3) {
                        top.linkTo(obesity2.bottom, 3.dp)
                        start.linkTo(parent.start, 8.dp)
                    }
            )

            Text(
                text = "18,5 ou menos",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(first) {
                    top.linkTo(parent.top, 5.dp)
                    end.linkTo(parent.end, 8.dp)
                }
            )
            Text(
                text = "Entre 18,6 e 24,9",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(second) {
                    top.linkTo(first.bottom, 3.dp)
                    end.linkTo(parent.end, 8.dp)
                }
            )
            Text(
                text = "Entre 25,0 e 29,9",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(third) {
                    top.linkTo(second.bottom, 3.dp)
                    end.linkTo(parent.end, 8.dp)
                }
            )
            Text(
                text = "Entre 30,0 e 34,9",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier.constrainAs(fourth) {
                    top.linkTo(third.bottom, 3.dp)
                    end.linkTo(parent.end, 8.dp)
                }
            )
            Text(
                text = "Entre 35,0 e 39,9",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier
                    .constrainAs(fifth) {
                        top.linkTo(fourth.bottom, 3.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
            )
            Text(
                text = "Acima de 40,0",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                modifier = textModifier
                    .padding(bottom = 3.dp)
                    .constrainAs(sixth) {
                        top.linkTo(fifth.bottom, 3.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
            )
        }
    }
}

@Preview
@Composable
fun CardViewTablePreview() {
    CardViewTable()
}