package ru.paskal.mathfacts.ui.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.utils.getColor

@Composable
fun InputLine(
    value: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    icon:  @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = null,
    @FloatRange(from = 0.0, to = 1.0) widthFraction: Float = 1f,
    label: @Composable (() -> Unit)? = null
) {
    TextField(
        label = label,
        value = value,
        readOnly = readOnly,
        singleLine = true,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(widthFraction),
        leadingIcon = icon,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = getColor(id = R.color.card_bga),
            focusedContainerColor = getColor(id = R.color.card_bga),
            focusedIndicatorColor = Color.Black
        ),
        keyboardOptions = if (keyboardOptions == null) { KeyboardOptions.Default} else {
            KeyboardOptions(keyboardType = KeyboardType.Decimal)
        }
    )
}