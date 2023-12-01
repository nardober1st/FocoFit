package com.fitfoco.focofit.navigation.mainnavgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val unselected: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home,
        unselected = Icons.Outlined.Home
    )

    object Menu : BottomBarScreen(
        route = "MENU",
        title = "MENU",
        icon = Icons.Default.Menu,
        unselected = Icons.Outlined.Menu
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings,
        unselected = Icons.Outlined.Settings
    )
}
