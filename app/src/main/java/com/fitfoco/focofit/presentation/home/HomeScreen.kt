package com.fitfoco.focofit.presentation.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fitfoco.focofit.ui.theme.Blue02
import com.fitfoco.focofit.ui.theme.White
import com.fitfoco.focofit.viewmodel.HomeViewModel
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.fitfoco.focofit.R
import com.fitfoco.focofit.components.BottomBarComponent
import com.fitfoco.focofit.navigation.AppNavigator
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val lazyListItem: MutableList<ListItem> = mutableListOf(
        ListItem(
            title = "Indice de massa corporal",
            icon = ImageVector.vectorResource(id = R.drawable.ic_imc)
        ),
        ListItem(
            title = "Calorias",
            icon = ImageVector.vectorResource(id = R.drawable.ic_calorias)
        ),
        ListItem(
            title = "Objetivos",
            icon = ImageVector.vectorResource(id = R.drawable.ic_obj)
        ),
        ListItem(
            title = "Alongamentos",
            icon = ImageVector.vectorResource(id = R.drawable.ic_along)
        ),
        ListItem(
            title = "Rotinas Diarias",
            icon = ImageVector.vectorResource(id = R.drawable.ic_rotinas)
        ),
        ListItem(
            title = "Notificacoes",
            icon = ImageVector.vectorResource(id = R.drawable.ic_not)
        ),
    )

    val items = listOf<BottomNavigationItem>(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Menu",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }

    val context = LocalContext.current

    val nickname = homeViewModel.userName().collectAsState("").value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Olá, $nickname",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                },
                actions = {
                    TextButton(onClick = {
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.setTitle("Deseja mesmo sair?")
                        alertDialog.setMessage("Se voce sair, precisara logar novamente!")
                        alertDialog.setPositiveButton("Sim") { _, _ ->
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("loginScreen")
                        }
                        alertDialog.setNegativeButton("Nao") { _, _ -> }
                            .show()
                    }) {
                        Text(
                            text = "Out",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Blue02
                )
            )
        },
        bottomBar = {
            AppNavigator()
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = BlueBackground
        ) {
            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                itemsIndexed(lazyListItem) { position, _ ->
                    ListPosition(
                        list = lazyListItem,
                        position = position,
                        context = context,
                        navController
                    )
                }
            }
        }
    }
}