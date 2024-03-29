package ru.paskal.mathfacts.ui.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.utils.getColor


@Composable
fun ButtonImpl(
    text: String,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 1.0) widthFraction: Float = 1f,
    enabled: MutableState<Boolean> = remember { mutableStateOf(true)}
    ) {
    Button(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(widthFraction),
        onClick = onClickAction,
        colors = ButtonDefaults.buttonColors(
            containerColor = getColor(id = R.color.button)
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp
        ),
        enabled = enabled.value
    ) {
        Text(
            text = text,
            color = getColor(id = R.color.font)
        )
    }
}