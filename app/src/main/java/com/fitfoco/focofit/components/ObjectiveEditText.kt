package com.fitfoco.focofit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitfoco.focofit.data.model.ExerciseFrequency
import com.fitfoco.focofit.presentation.objective.ObjetivoViewModel
import com.fitfoco.focofit.ui.theme.Blue01
import com.fitfoco.focofit.ui.theme.Orange
import com.fitfoco.focofit.ui.theme.Outline
import com.fitfoco.focofit.ui.theme.ShapeEdit
import com.fitfoco.focofit.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectiveEditText(
    modifier: Modifier = Modifier,
    viewModel: ObjetivoViewModel = hiltViewModel()
) {

    val list = listOf(
        ExerciseFrequency.OnceAWeek,
        ExerciseFrequency.TwiceAWeek,
        ExerciseFrequency.ThreeAWeek,
        ExerciseFrequency.FourWeek,
        ExerciseFrequency.FiveAWeek,
        ExerciseFrequency.SixAWeek,
        ExerciseFrequency.SevenWeek,
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Box(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 2.dp,
                color = Orange,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = modifier
                .clickable {
                    expanded = !expanded
                }
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = viewModel.selectedFrequency.frequency,
                color = Orange,
                modifier = Modifier.padding(start = 16.dp)
            )
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp) // Padding for the icon
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    modifier = modifier.background(White),
                    onClick = {
                        viewModel.selectedFrequency = label
                        expanded = false
                    }
                ) {
                    Text(
                        text = label.frequency,
                        color = Orange
                    )
                }
            }
        }
    }
}
