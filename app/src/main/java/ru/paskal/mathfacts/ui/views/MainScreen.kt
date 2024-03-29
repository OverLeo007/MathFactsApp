package ru.paskal.mathfacts.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.navigation.NavGraph
import ru.paskal.mathfacts.ui.navigation.Navigation
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.utils.getColor

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
        NavGraph(navHostController = navController, padding = it)
    }
}