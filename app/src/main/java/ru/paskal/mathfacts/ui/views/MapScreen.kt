package ru.paskal.mathfacts.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.components.ButtonImpl
import ru.paskal.mathfacts.ui.components.InputLine
import ru.paskal.mathfacts.ui.navigation.Routes
import ru.paskal.mathfacts.utils.getColor
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun MapScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    var cordX by remember { mutableStateOf(Random.nextInt(1..99).toString()) }
    var cordY by remember { mutableStateOf(Random.nextInt(1..99).toString()) }
    Column (
        modifier = Modifier
        .fillMaxSize()

    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8F)
//            .height(120.dp)
//            .shadow(4.dp)
//            .clip(RoundedCornerShape(16.dp))
                .background(getColor(R.color.bga)),
            painter = painterResource(R.drawable.map90),
            contentDescription = "Description for accessibility",
            contentScale = ContentScale.Crop
        )
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
                        value = cordX,
                        readOnly = true,
                        onValueChange = {
                            cordX = it
                        },
                        label = { Text(text = "X координата")},
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