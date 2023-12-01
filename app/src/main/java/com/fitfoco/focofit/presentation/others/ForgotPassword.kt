package com.fitfoco.focofit.presentation.others

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fitfoco.focofit.R
import com.fitfoco.focofit.components.EditText
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.presentation.login.LoginViewModel

@Composable
fun ForgotPassword(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditText(
            label = "Email", value = email,
            onValueChanged = {
                email = it
            },
            keyboardType = KeyboardType.Email,
            modifier = Modifier,
            trailingIcon = { null }
        )
        Button(onClick = {
            viewModel.forgotPassword(email, object : ListenerAuth {
                override fun onSuccess(message: String, screen: String) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    navController.navigate(screen)
                }

                override fun onFailure(error: String) {
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
            })
        }) {

            Text(text = stringResource(id = R.string.send))

        }
    }
}

