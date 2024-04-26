package ru.paskal.mathfacts.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.viewmodel.MapViewModel
import kotlin.math.roundToInt

@Composable
fun MapScreen(
    navHostController: NavHostController,
    vm: MapViewModel
) {
    Log.d("VM INIT MapScreen", vm.toString())
    val cameraPositionState = rememberCameraPositionState {
        position = vm.cameraPosition
    }

    LaunchedEffect(cameraPositionState.position) {
        vm.cameraPosition = cameraPositionState.position
    }



    Column (
        modifier = Modifier
        .fillMaxSize()

    ){
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8F),
            cameraPositionState = cameraPositionState,
            onMapClick =  { latLng ->
                vm.curLatLng = latLng
                vm.markerState = MarkerState(position = latLng)
            },
            properties = MapProperties(mapType = MapType.SATELLITE),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true,
            )
        ) {
            Marker(
                state = MarkerState(position = vm.ikitLatLng),
                title = "Хихит",
                snippet = "Вот тут я сдаю лабы"
            )
            vm.markerState?.let {
                Marker(
                    state = it,
                    title = "Clicked location",
                    snippet = "Lat: ${it.position.latitude}, Lng: ${it.position.longitude}"
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Column(
                    modifier = Modifier.weight(0.8F),
                ) {
                    InputLine(
                        value = vm.curLatLng.latitude.roundToInt().toString(),
                        readOnly = true,
                        onValueChange = {
                        },
                        label = { Text(text = "X координата")},
                    )
                }
                Spacer(modifier = Modifier.weight(0.2F))
                Column(
                    modifier = Modifier.weight(0.8F),
                ) {
                    InputLine(
                        value = vm.curLatLng.longitude.roundToInt().toString(),
                        readOnly = true,
                        onValueChange = {
                        },
                        label = { Text(text = "Y координата")},
                    )
                }
            }
            ButtonImpl(
                text = "Подтвердить",
                onClickAction = {
                    navHostController.navigate(Routes.CoordsScreenRoute)
                },
            )
        }

    }
}