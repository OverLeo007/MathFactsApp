package ru.paskal.mathfacts.ui.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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

@Preview(showBackground = true, backgroundColor = 0xFFF4EE)
@Composable
fun CoordsScreen() {
    val context = LocalContext.current
    var cordX by remember { mutableStateOf(Random.nextInt(1..99).toString()) }
    var cordY by remember { mutableStateOf(Random.nextInt(1..99).toString()) }
    val isGenButtonEnabled = remember {
        mutableStateOf(true)
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
                    value = cordX,
                    readOnly = false,
                    onValueChange = {
                        cordX = it
                        isGenButtonEnabled.value = cordX.isDigitsOnly() && cordY.isDigitsOnly()
                    },
                    icon = {
                        IconButton(onClick = {
                            cordX = Random.nextInt(Random.nextInt(1..99)).toString()
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

                    text = "Сгенерировать по X",
                    onClickAction = {
                        Toast.makeText(context, "Генерация факта по X", Toast.LENGTH_SHORT).show()
                    },
                    enabled = isGenButtonEnabled
                )
            }
            Spacer(modifier = Modifier.weight(0.2F))
            Column(
                modifier = Modifier.weight(0.8F),
            ) {
                InputLine(
                    value = cordY,
                    readOnly = false,
                    onValueChange = {
                        cordY = it
                        isGenButtonEnabled.value = cordX.isDigitsOnly() && cordY.isDigitsOnly()
                    },
                    icon = {
                        IconButton(onClick = {
                            cordY = Random.nextInt(Random.nextInt(1..99)).toString()
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
                    text = "Сгенерировать по Y",
                    onClickAction = {
                        Toast.makeText(context, "Генерация факта по Y", Toast.LENGTH_SHORT).show()
                    },
                    enabled = isGenButtonEnabled
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.map),
            contentDescription = "Description for accessibility"
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







