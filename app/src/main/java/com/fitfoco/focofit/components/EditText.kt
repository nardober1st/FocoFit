package com.fitfoco.focofit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fitfoco.focofit.ui.theme.Blue01
import com.fitfoco.focofit.ui.theme.Outline
import com.fitfoco.focofit.ui.theme.ShapeEdit
import com.fitfoco.focofit.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier,
    trailingIcon: () -> Unit?
) {

    OutlinedTextField(
        value,
        onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = White,
//            text = Outline,
            focusedLabelColor = Blue01,
            unfocusedLabelColor = Outline,
            focusedBorderColor = Blue01,
            unfocusedBorderColor = Outline,
            cursorColor = Blue01
        ),
        shape = ShapeEdit.small,
        trailingIcon = {}
        )
}
