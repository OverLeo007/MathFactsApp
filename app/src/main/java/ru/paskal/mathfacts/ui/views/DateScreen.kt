package ru.paskal.mathfacts.ui.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import ru.paskal.mathfacts.viewmodel.FactsViewModel

@Composable
fun DateScreen(
    vm: FactsViewModel
) {
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
        InputLine(
            value = vm.currentInput,
            readOnly = false,
            onValueChange = { vm.updateInput(it) },
            onKeyboardDone = { vm.generateAndSetFact() },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.date_icon),
                    contentDescription = "Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
            buttonOnClick = { vm.saveCurrentFact() },
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