package com.fitfoco.focofit.components

//import android.app.AlertDialog
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.fitfoco.focofit.R
//import com.fitfoco.focofit.ui.theme.Blue02
//import com.fitfoco.focofit.ui.theme.White
//import com.google.firebase.auth.FirebaseAuth
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Toolbar() {
//
//
//    TopAppBar(
//        title = {
//            Text(
//                text = "Olá, Boico",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = White,
//                modifier = Modifier.padding(start = 10.dp)
//            )
//        },
//        navigationIcon = {
//            Image(
//                painter = painterResource(id = R.drawable.ic_about),
//                contentDescription = "",
//                modifier = Modifier.padding(start = 10.dp).clickable {
//
//                }
//            )
//        },
//        actions = {
//
//
//            TextButton(onClick = {
//                val alertDialog = AlertDialog.Builder(context)
//                alertDialog.setTitle("Deseja mesmo sair?")
//                alertDialog.setMessage("Se voce sair, precisara logar novamente!")
//                alertDialog.setPositiveButton("Sim") { _, _ ->
//                    FirebaseAuth.getInstance().signOut()
//                    navController.navigate("loginScreen")
//                }
//                alertDialog.setNegativeButton("Nao") { _, _ -> }
//                    .show()
//            }) {
//                Text(
//                    text = "Out",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = White
//                )
//            }
//        },
//        colors = TopAppBarDefaults.smallTopAppBarColors(
//            containerColor = Blue02
//        )
//    )
//}
//
//@Preview
//@Composable
//fun ToolbarPreview() {
//    Toolbar()
//}

