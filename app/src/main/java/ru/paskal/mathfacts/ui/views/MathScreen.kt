package ru.paskal.mathfacts.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun MathScreen() {
    var number by remember { mutableStateOf(Random.nextInt(1..1000).toString()) }
    val context = LocalContext.current
    val isGenButtonEnabled = remember {
        mutableStateOf(true)
    }

    MainColumn {
        val titleModifier = Modifier.align(Alignment.Start)
        TitleText(
            text = "Сгенерировать факт",
            modifier = titleModifier
        )

        InputLine(
            value = number,
            readOnly = false,
            onValueChange = {
                number = it
                isGenButtonEnabled.value = number.isDigitsOnly()
            },
            icon = {
                IconButton(onClick = {
                    number = Random.nextInt(1..1000).toString()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.random),
                        contentDescription = "Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        ButtonImpl(
            text = "Сгенерировать",
            onClickAction = {
                Toast.makeText(context, "Генерация факта", Toast.LENGTH_SHORT).show()
            },
            widthFraction = 0.7F,
            enabled = isGenButtonEnabled
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Интересный факт", modifier = titleModifier)
        CardImpl(
            text = "3306 is the number of non-associative " +
                    "closed binary operations on a set with 3 elements.",
            buttonOnClick = {
                Toast.makeText(context, "Сохранение факта", Toast.LENGTH_SHORT).show()
            }
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Сохраненные факты", modifier = titleModifier)
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SavedListCard("3306 is the number of non-associative " +
                "closed binary operations on a set with 3 elements.")
    }
}