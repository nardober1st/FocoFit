package com.fitfoco.focofit.presentation.others

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitfoco.focofit.R
import com.fitfoco.focofit.ui.theme.Blue02
import com.fitfoco.focofit.ui.theme.Blue03
import com.fitfoco.focofit.ui.theme.BlueBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RotinasDiariasScreen() {

    Scaffold {
        Column(
            modifier = Modifier
                .background(BlueBackground)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (txt, btn) = createRefs()

                Text(
                    text = "Adicione uma nova rotina diária",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue03,
                    modifier = Modifier.constrainAs(txt) {
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                IconButton(onClick = { }, modifier = Modifier.constrainAs(btn) {
                    bottom.linkTo(parent.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RotinasDiariasScreenPreview() {
    RotinasDiariasScreen()
}