package ru.paskal.mathfacts.ui.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import ru.paskal.mathfacts.viewmodels.date.DateViewModel

@Composable
fun DateScreen(
    vm: DateViewModel = viewModel(factory = DateViewModel.factory)
) {
    val rating = remember {
        mutableIntStateOf(0)
    }


    LaunchedEffect(rating.intValue) {
        vm.updateRating(rating.intValue)
    }
    val context = LocalContext.current

    MainColumn {
        val titleModifier = Modifier.align(Alignment.Start)
        TitleText(
            text = "Сгенерировать факт",
            modifier = titleModifier
        )
        InputLine(
            value = vm.currentInput,
            readOnly = false,
            onValueChange = { vm.updateInput(it) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.date_icon),
                    contentDescription = "Icon"
                )
            },
        )
        ButtonImpl(
            text = "Сгенерировать",
            onClickAction = {
                vm.generateAndSetFact()
            },
            widthFraction = 0.7F,
            enabled = vm.isInputCorrect
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Интересный факт", modifier = titleModifier)
        CardImpl(
            text = vm.currentFact.factText,
            buttonOnClick = {
                vm.saveCurrentFact()
            },
            rating = rating,
            isLoading = vm.isLoading
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