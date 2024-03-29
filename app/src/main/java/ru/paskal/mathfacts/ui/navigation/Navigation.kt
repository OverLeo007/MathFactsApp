package ru.paskal.mathfacts.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.utils.getColor


@Composable
fun Navigation(
    navController: NavController,
    modifier: Modifier
) {
    val list = listItems
    NavigationBar(
        containerColor = getColor(R.color.gray),
        modifier = modifier
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        list.forEach { item ->
            val isSelected = remember { mutableStateOf(false) }
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    isSelected.value = true
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(item.iconId), contentDescription = "Icon")
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
                alwaysShowLabel = false
            )
        }
    }
}




