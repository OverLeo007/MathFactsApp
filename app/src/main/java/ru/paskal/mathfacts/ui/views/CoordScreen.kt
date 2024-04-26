package ru.paskal.mathfacts.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.utils.getColor
import ru.paskal.mathfacts.viewmodel.MapViewModel
import kotlin.math.roundToInt

@Composable
fun CoordsScreen(
    navHostController: NavHostController,
    vm: MapViewModel
) {
    Log.d("VM INIT CoordsScreen", vm.toString())
    val rating = remember {
        mutableIntStateOf(vm.currentFact.rating)
    }

    LaunchedEffect(rating.intValue) {
        vm.updateRating(rating.intValue)
    }

    MainColumn {
        val titleModifier = Modifier.align(Alignment.Start)
        TitleText(
            text = "Сгенерировать факт",
            modifier = titleModifier
        )
        Row(
            modifier = Modifier.fillMaxWidth(),

        ) {
            Column(
                modifier = Modifier.weight(0.8F),
            ) {
                InputLine(
                    value = vm.curLatLng.latitude.roundToInt().toString(),
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = "X координата")},
                )
                ButtonImpl(
                    text = "Сгенерировать по X",
                    onClickAction = {
                        vm.generateAndSetFact("X")
                    },
                )
            }
            Spacer(modifier = Modifier.weight(0.2F))
            Column(
                modifier = Modifier.weight(0.8F),
            ) {
                InputLine(
                    value = vm.curLatLng.longitude.roundToInt().toString(),
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(text = "Y координата") },
                )
                ButtonImpl(
                    text = "Сгенерировать по Y",
                    onClickAction = {
                        vm.generateAndSetFact("Y")
                    },
                )
            }
        }
        // <--------------------------------------------------------------------------------------->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .shadow(4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(getColor(R.color.bga))
                .clickable(onClick = {navHostController.navigate(Routes.MapScreenRoute) }),
            painter = painterResource(R.drawable.map_button),
            contentDescription = "Description for accessibility",
            contentScale = ContentScale.Crop
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Интересный факт", modifier = titleModifier)
        CardImpl(
            text = vm.currentFact.factText,
            buttonOnClick = { vm.saveCurrentFact() },
            rating = rating,
            isLoading = vm.isLoading,
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Сохраненные факты", modifier = titleModifier)
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SavedListCard(
            facts = vm.savedFacts,
            onSelectItemInDropdown = { vm.updateSorting(it) },
            onDeleteFact = { vm.deleteFact(it) },
            onSaveFact = { vm.saveFact(it) }
        )
    }

}







