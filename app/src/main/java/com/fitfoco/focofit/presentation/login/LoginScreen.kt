package com.fitfoco.focofit.presentation.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fitfoco.focofit.R
import com.fitfoco.focofit.components.ButtonEdit
import com.fitfoco.focofit.components.EditText
import com.fitfoco.focofit.listener.ListenerAuth
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.Black80
import com.fitfoco.focofit.ui.theme.Blue01
import com.fitfoco.focofit.ui.theme.Blue03
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.Outline
import com.fitfoco.focofit.ui.theme.ShapeEdit
import com.fitfoco.focofit.ui.theme.White
import com.fitfoco.focofit.viewmodel.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController, viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var userCheck = viewModel.checkUser().collectAsState(initial = false).value

    LaunchedEffect(userCheck){
        if (userCheck){
            navController.navigate("homeScreen")
        } else {
            userCheck = false
        }
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var visibility by remember {
        mutableStateOf(false)
    }

    val icon = if (visibility) {
        painterResource(id = R.drawable.baseline_visibility_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueBackground)
        ) {

            ConstraintLayout {

                val (welcome, edtEmail, edtPassword, btn, txtForget, txtNew, or, face, gmail) = createRefs()

                Text(text = stringResource(id = R.string.welcome),
                    color = Blue03,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(welcome) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 130.dp)
                    })

                EditText(
                    label = stringResource(id = R.string.email),
                    value = email,
                    onValueChanged = { email = it },
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.constrainAs(edtEmail) {
                        top.linkTo(welcome.bottom, 75.dp)
                    },
                    trailingIcon = { null }
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(edtPassword) {
                            top.linkTo(edtEmail.bottom)

                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = White,
//                        textColor = Outline,
                        focusedLabelColor = Blue01,
                        unfocusedLabelColor = Outline,
                        focusedBorderColor = Blue01,
                        unfocusedBorderColor = Outline,
                        cursorColor = Blue01
                    ),
                    maxLines = 1,
                    shape = ShapeEdit.small,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        IconButton(onClick = { visibility = !visibility }) {
                            Icon(painter = icon, contentDescription = "")
                        }
                    },
                    visualTransformation = if (visibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                ButtonEdit(onClick = {
                    viewModel.login(email, password, object : ListenerAuth {
                        override fun onSuccess(message: String, screen: String) {
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            navController.navigate(screen)
                        }

                        override fun onFailure(error: String) {
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }
                    })
                },
                    text = stringResource(id = R.string.entrar),
                    modifier = Modifier
                        .height(53.dp)
                        .constrainAs(btn) {
                            top.linkTo(edtPassword.bottom, 20.dp)
                        })

                TextButton(onClick = {
                    navController.navigate("forgotPassword")
                }, modifier = Modifier.constrainAs(txtForget) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(btn.bottom, 20.dp)
                }) {
                    Text(
                        text = stringResource(id = R.string.forgot),
                        color = Black80,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }

                TextButton(onClick = { navController.navigate("signupScreen") },
                    modifier = Modifier.constrainAs(txtNew) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(txtForget.bottom, 60.dp)
                    }) {
                    Text(
                        text = stringResource(id = R.string.novo),
                        color = Black80,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }

                Text(text = stringResource(id = R.string.or),
                    fontSize = 15.sp,
                    color = Black80,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(or) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(txtNew.bottom, 10.dp)
                    })

                Button(
                    onClick = { },
                    modifier = Modifier
                        .border(2.dp, White, ShapeEdit.medium)
                        .constrainAs(face) {
                            top.linkTo(or.bottom, 20.dp)
                            start.linkTo(parent.start, 100.dp)
                        },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue01
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.face),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = White
                    )
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .border(2.dp, Black, ShapeEdit.medium)
                        .constrainAs(gmail) {
                            top.linkTo(or.bottom, 20.dp)
                            end.linkTo(parent.end, 100.dp)
                        },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.gmail),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Black
                    )
                }
            }
        }
    }
}