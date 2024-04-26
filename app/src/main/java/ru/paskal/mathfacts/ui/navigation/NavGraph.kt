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
import ru.paskal.mathfacts.viewmodel.MapViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    padding: PaddingValues,
    vms: HashMap<String, FactsViewModel>,
    mapVm: MapViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.MapScreenRoute,
        Modifier.padding(padding)
    ) {
        composable(Routes.CoordsScreenRoute) {
            CoordsScreen(navHostController = navHostController, vm = mapVm)
        }
        composable(Routes.MapScreenRoute) {
            MapScreen(navHostController = navHostController, vm = mapVm)
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
    }
}