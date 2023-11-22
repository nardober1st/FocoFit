package com.fitfoco.focofit.presentation.objective

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitfoco.focofit.components.ButtonEdit
import com.fitfoco.focofit.components.EditText
import com.fitfoco.focofit.components.SelectableButton
import com.fitfoco.focofit.data.model.ExerciseFrequency
import com.fitfoco.focofit.data.model.ObjetivosFit
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.viewmodel.ObjetivoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjetivosScreen(
    viewModel: ObjetivoViewModel = hiltViewModel()
) {
    var peso by remember {
        mutableStateOf("")
    }
    var altura by rememberSaveable {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    val list = listOf<ExerciseFrequency>(
        ExerciseFrequency.SevenWeek,
        ExerciseFrequency.OnceAWeek,
        ExerciseFrequency.FourWeek
    )

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
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
                    text = "Mulher",
                    modifier = Modifier,
                    isSelected = viewModel.selectedGoal is ObjetivosFit.Ganhar,
                    onClick = {
                        viewModel.onObjetivoClick(ObjetivosFit.Ganhar)
                    })
                SelectableButton(
                    text = "Homem",
                    modifier = Modifier,
                    isSelected = viewModel.selectedGoal is ObjetivosFit.Emagrecer,
                    onClick = {
                        viewModel.onObjetivoClick(ObjetivosFit.Emagrecer)
                    })
            }
        }
        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = viewModel.selectedFrequency.frequency,
                onValueChange = {
                    viewModel.selectedFrequency.frequency = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            expanded = !expanded
                        })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                list.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.selectedFrequency = label
                        }) {
                        Text(text = label.frequency)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 80.dp))
        ButtonEdit(
            text = "Salvar",
            onClick = {
            },
            modifier = Modifier
        )
    }
}
