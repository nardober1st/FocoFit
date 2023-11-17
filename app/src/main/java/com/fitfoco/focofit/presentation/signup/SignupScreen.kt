package com.fitfoco.focofit.presentation.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.fitfoco.focofit.components.ButtonEdit
import com.fitfoco.focofit.components.DataPickerButton
import com.fitfoco.focofit.components.EditText
import com.fitfoco.focofit.components.SelectableButton
import com.fitfoco.focofit.data.model.Gender
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.White
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel
) {

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .format(pickedDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    var name by remember {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var apelido by rememberSaveable {
        mutableStateOf("")
    }
    var senha by rememberSaveable {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(BlueBackground)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp, top = 40.dp),
                text = "Cadastre-se!",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    text = "Nome"
                )
                EditText(
                    label = "Nome",
                    value = name,
                    onValueChanged = {
                        name = it
                    },
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    text = "Email"
                )
                EditText(
                    label = "Email",
                    value = email,
                    onValueChanged = {
                        email = it
                    },
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    text = "Apelido"
                )
                EditText(
                    label = "Apelido",
                    value = apelido,
                    onValueChanged = {
                        apelido = it
                    },
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    text = "Senha"
                )
                EditText(
                    label = "Senha",
                    value = senha,
                    onValueChanged = {
                        senha = it
                    },
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    text = "Sexo"
                )
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        SelectableButton(
                            text = "Homem",
                            isSelected = viewModel.selectedGender is Gender.Male,
                            onClick = {
                                viewModel.onGenderClick(Gender.Male)
                            })
                        SelectableButton(
                            text = "Mulher",
                            isSelected = viewModel.selectedGender is Gender.Female,
                            onClick = {
                                viewModel.onGenderClick(Gender.Female)
                            })
                    }
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    text = "Data de Nascimento:"
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                DataPickerButton(
                    text = formattedDate,
                    onClick = {
                        dateDialogState.show()
                    }
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                ButtonEdit(
                    text = "Salvar",
                    onClick = {},
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
            }
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        backgroundColor = White,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = TextStyle(
                    color = Orange
                )
            )
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now(),
            "Selecione sua data de nascimento",
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = Orange,
                headerTextColor = White,
                calendarHeaderTextColor = Orange,
                dateActiveBackgroundColor = Orange
            )
        ) {
            pickedDate = it
        }
    }
}