package ru.paskal.mathfacts.utils

import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun getColor(@ColorRes id: Int): Color {
    return Color(LocalContext.current.resources.getColor(id, null))
}