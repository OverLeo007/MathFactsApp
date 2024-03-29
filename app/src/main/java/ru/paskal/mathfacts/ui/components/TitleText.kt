package ru.paskal.mathfacts.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.paskal.mathfacts.ui.theme.AppTypography

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier
            .padding(vertical = 10.dp),
        style = AppTypography.titleMedium
    )
}