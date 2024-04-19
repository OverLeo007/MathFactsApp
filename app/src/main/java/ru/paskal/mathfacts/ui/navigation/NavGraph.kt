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
import ru.paskal.mathfacts.utils.FactTypes
import ru.paskal.mathfacts.viewmodel.FactsViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    padding: PaddingValues,
    vms: HashMap<String, FactsViewModel>
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.MathScreenRoute,
        Modifier.padding(padding)
    ) {
        composable(Routes.CoordsScreenRoute) {
            CoordsScreen(navHostController = navHostController)
        }
        composable(Routes.DateScreenRoute) {
            DateScreen(vm = vms[FactTypes.Date]!!)
        }
        composable(Routes.RegularScreenRoute) {
            RegularScreen(vm = vms[FactTypes.Trivia]!!)
        }
        composable(Routes.MathScreenRoute) {
            MathScreen(vm = vms[FactTypes.Math]!!)
        }
        composable(Routes.MapScreenRoute) {
            MapScreen(navHostController = navHostController)
        }
    }
}