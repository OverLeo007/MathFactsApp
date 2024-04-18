package ru.paskal.mathfacts.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.paskal.mathfacts.ui.views.CoordsScreen
import ru.paskal.mathfacts.ui.views.DateScreen
import ru.paskal.mathfacts.ui.views.MapScreen
import ru.paskal.mathfacts.ui.views.MathScreen
import ru.paskal.mathfacts.ui.views.RegularScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.DateScreenRoute,
        Modifier.padding(padding)
    ) {
        composable(Routes.CoordsScreenRoute) {
            CoordsScreen(navHostController = navHostController)
        }
        composable(Routes.DateScreenRoute) {
            DateScreen()
        }
        composable(Routes.RegularScreenRoute) {
            RegularScreen()
        }
        composable(Routes.MathScreenRoute) {
            MathScreen()
        }
        composable(Routes.MapScreenRoute) {
            MapScreen(navHostController = navHostController)
        }
    }
}