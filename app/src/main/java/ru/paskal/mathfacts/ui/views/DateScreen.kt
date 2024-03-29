package ru.paskal.mathfacts.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview(showBackground = true, backgroundColor = 0xFFF4EE)
@Composable
fun DateScreen() {
    var date by remember { mutableStateOf("29.03.2024") }
    val context = LocalContext.current

    MainColumn {
        val titleModifier = Modifier.align(Alignment.Start)
        TitleText(
            text = "Сгенерировать факт",
            modifier = titleModifier
        )
        InputLine(
            value = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")),
            readOnly = true,
            onValueChange = { newText -> date = newText },
            icon = {
                IconButton(onClick = {
                    Toast.makeText(context, "Выбор даты", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.date_icon),
                        contentDescription = "Icon"
                    )
                }
            }
        )
        ButtonImpl(
            text = "Сгенерировать",
            onClickAction = {
                Toast.makeText(context, "Генерация факта", Toast.LENGTH_SHORT).show()
            },
            widthFraction = 0.7F
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Интересный факт", modifier = titleModifier)
        CardImpl(
            text = "March 29th is the day in 1990 that the Czechoslovak " +
                    "parliament is unable to reach an agreement on what to call " +
                    "the country after the fall of Communism, sparking the so-called Hyphen War.",
            buttonOnClick = {
                Toast.makeText(context, "Сохранение факта", Toast.LENGTH_SHORT).show()
            }
        )
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleText(text = "Сохраненные факты", modifier = titleModifier)
        // <--------------------------------------------------------------------------------------->
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SavedListCard("March 29th is the day in 1990 that the Czechoslovak " +
                "parliament is unable to reach an agreement on what to call " +
                "the country after the fall of Communism, sparking the so-called Hyphen War.")
    }
}