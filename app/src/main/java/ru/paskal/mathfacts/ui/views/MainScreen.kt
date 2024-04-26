package ru.paskal.mathfacts.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.navigation.NavGraph
import ru.paskal.mathfacts.ui.navigation.Navigation
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.utils.FactTypes
import ru.paskal.mathfacts.utils.getColor
import ru.paskal.mathfacts.viewmodel.FactsViewModel
import ru.paskal.mathfacts.viewmodel.FactsVmFactory
import ru.paskal.mathfacts.viewmodel.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val title = when (currentRoute) {
        Routes.CoordsScreenRoute -> "Факты по координатам"
        Routes.DateScreenRoute -> "Факты по дате"
        Routes.RegularScreenRoute -> "Обычные факты"
        Routes.MathScreenRoute -> "Математические факты"
        Routes.MapScreenRoute -> "Карта"
        else -> ""
    }

    val vms: HashMap<String, FactsViewModel> = HashMap()
    vms[FactTypes.Date] = viewModel(key = FactTypes.Date, factory = FactsVmFactory(FactTypes.Date, FactsViewModel::class.java))
    vms[FactTypes.Trivia] = viewModel(key = FactTypes.Trivia, factory = FactsVmFactory(FactTypes.Trivia, FactsViewModel::class.java))
    vms[FactTypes.Math] = viewModel(key = FactTypes.Math, factory = FactsVmFactory(FactTypes.Math, FactsViewModel::class.java))
    val mapVm: MapViewModel = viewModel(key = FactTypes.Map, factory = FactsVmFactory(FactTypes.Map, MapViewModel::class.java))

    Log.d("VMS", vms.toString())

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp),
                colors = topAppBarColors(
                    containerColor = getColor(R.color.gray),
                    titleContentColor = Color.White
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = title)
                    }
                }
            )
        },
        bottomBar = {
            Navigation(
                navController = navController,
                modifier = Modifier.height(48.dp)
            )
        },
        containerColor = getColor(id = R.color.bga)
    ) {
        Log.d("nav p", it.toString())
        NavGraph(navHostController = navController, padding = it, vms = vms, mapVm = mapVm)
    }
}