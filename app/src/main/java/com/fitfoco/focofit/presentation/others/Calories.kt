package com.fitfoco.focofit.presentation.others


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitfoco.focofit.components.CardView
import com.fitfoco.focofit.presentation.home.BottomNavigationItem
import com.fitfoco.focofit.ui.theme.Black
import com.fitfoco.focofit.ui.theme.Blue02
import com.fitfoco.focofit.ui.theme.BlueBackground
import com.fitfoco.focofit.ui.theme.White
import com.fitfoco.focofit.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Calories(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

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

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }


    val context = LocalContext.current
    val nickname = homeViewModel.userName().collectAsState("").value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = " $nickname",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Blue02
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Blue02
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    ) {
        Column (
            modifier = Modifier.background(BlueBackground)
        ){
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (name, txt, txtBig, card, kcal) = createRefs()

                Text(
                    text = " $nickname, ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    modifier = Modifier.constrainAs(name) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(parent.top, 20.dp)
                    }
                )
                Text(
                    text = " a quantidade de calorias diárias que você deve consumir é: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Black,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .constrainAs(txt) {
                            start.linkTo(parent.start, 20.dp)
                            top.linkTo(name.bottom)
                        }
                )
                Text(
                    text = "Emagrecimento",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    modifier = Modifier.constrainAs(txtBig) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(txt.bottom, 20.dp)
                    }
                )
                CardView(
                    modifier = Modifier.constrainAs(card){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(txtBig.bottom, 20.dp)
                    }
                )
                Text(
                    text = "Kcal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    modifier = Modifier.constrainAs(kcal) {
                        start.linkTo(card.end, 3.dp)
                        top.linkTo(txtBig.bottom, 40.dp)
                    }
                )
            }
        }
    }
}

