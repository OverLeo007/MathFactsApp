package ru.paskal.mathfacts.ui.views

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.CardImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.components.MainColumn
import ru.paskal.mathfacts.ui.components.SavedListCard
import ru.paskal.mathfacts.ui.components.TitleText
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.utils.getColor
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun CoordsScreen(navHostController: NavHostController) {
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
                    readOnly = true,
                    onValueChange = {
                        cordX = it
                    },
                    label = { Text(text = "X координата")},
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
                    readOnly = true,
                    onValueChange = {
                        cordY = it
                    },
                    label = { Text(text = "Y координата") },
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







