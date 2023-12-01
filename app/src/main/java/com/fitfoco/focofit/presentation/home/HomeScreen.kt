package com.fitfoco.focofit.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.fitfoco.focofit.R
import com.fitfoco.focofit.ui.theme.BlueBackground

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

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

    Surface(
        modifier = Modifier
            .fillMaxSize(),
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
                    navController = navController
                )
            }
        }
    }
}