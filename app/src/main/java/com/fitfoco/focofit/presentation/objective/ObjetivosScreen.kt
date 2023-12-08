package com.fitfoco.focofit.presentation.objective

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fitfoco.focofit.components.ButtonEdit
import com.fitfoco.focofit.components.EditText
import com.fitfoco.focofit.components.ObjectiveEditText
import com.fitfoco.focofit.components.SelectableButton
import com.fitfoco.focofit.data.model.ExerciseFrequency
import com.fitfoco.focofit.data.model.Objetivo
import com.fitfoco.focofit.data.model.ObjetivosFit
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.navigation.homenavgraph.Screen
import com.fitfoco.focofit.ui.theme.BlueBackground

@Composable
fun ObjetivosScreen(
    viewModel: ObjetivoViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    var peso by remember {
        mutableStateOf("")
    }
    var altura by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueBackground)
    ) {
        Text(
            text = "Peso Atual",
            modifier = Modifier.padding(start = 20.dp, top = 40.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            EditText(
                value = peso,
                label = "Peso",
                onValueChanged = {
                    peso = it
                },
                modifier = Modifier
                    .weight(1f),
                keyboardType = KeyboardType.Decimal,
                trailingIcon = {}
            )
            Text(
                text = "Kg",
                modifier = Modifier.padding(end = 5.dp)
            )
        }
        Text(
            text = "Altura Atual",
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            EditText(
                value = altura,
                label = "Altura",
                onValueChanged = {
                    altura = it
                },
                modifier = Modifier
                    .weight(1f),
                keyboardType = KeyboardType.Decimal,
                trailingIcon = {}
            )
            Text(
                text = "Cm",
                modifier = Modifier.padding(end = 5.dp)
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp),
            text = "Objetivo"
        )
        Column(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                SelectableButton(
                    text = "Manter",
                    modifier = Modifier,
                    isSelected = viewModel.selectedGoal is ObjetivosFit.Manter,
                    onClick = {
                        viewModel.onObjetivoClick(ObjetivosFit.Manter)
                    })
                SelectableButton(
                    text = "Ganhar",
                    modifier = Modifier,
                    isSelected = viewModel.selectedGoal is ObjetivosFit.Ganhar,
                    onClick = {
                        viewModel.onObjetivoClick(ObjetivosFit.Ganhar)
                    })
                SelectableButton(
                    text = "Emagracer",
                    modifier = Modifier,
                    isSelected = viewModel.selectedGoal is ObjetivosFit.Emagrecer,
                    onClick = {
                        viewModel.onObjetivoClick(ObjetivosFit.Emagrecer)
                    })
            }
        }
        Column {
            Text(
                text = "Frequenca fisica semanal:",
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )
            ObjectiveEditText()
        }
        Spacer(modifier = Modifier.padding(top = 80.dp))
        ButtonEdit(
            text = "Salvar",
            onClick = {
                val alturaFloat = altura.toFloat() / 100
                val imcResult = peso.toFloat() / (alturaFloat * alturaFloat)
                val imcFormatted = String.format("%.2f", imcResult) // formatting BMI to two decimal places
                val objetivo = Objetivo(
                    peso = peso,
                    altura = altura,
                    goal = viewModel.selectedGoal,
                    exerciseFrequency = viewModel.selectedFrequency,
                    imcResult = imcFormatted
                )
                viewModel.saveObjetivo(objetivo, object : ListenerAuth {
                    override fun onSuccess(message: String, screen: String) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        navController.navigate(screen) {
                            popUpTo(Screen.Objetivos.route) {
                                inclusive = true
                            }
                        }
                    }

                    override fun onFailure(error: String) {
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    }

                })
            },
            modifier = Modifier
        )
    }
}
