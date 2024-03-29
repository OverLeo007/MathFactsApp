package ru.paskal.mathfacts.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import ru.paskal.mathfacts.R

object AppFont {
    val GothamPro = FontFamily(
        Font(R.font.gothampro),
        Font(R.font.gothampro_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.gothampro_black, FontWeight.Black),
        Font(R.font.gothampro_blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.gothampro_bold, FontWeight.Bold),
        Font(R.font.gothampro_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.gothampro_light, FontWeight.Light),
        Font(R.font.gothampro_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.gothampro_medium, FontWeight.Medium),
        Font(R.font.gothampro_mediumitalic, FontWeight.Medium, FontStyle.Italic)
    )
}

private val defaultTypography = Typography()
val AppTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.GothamPro),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.GothamPro),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.GothamPro),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.GothamPro),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.GothamPro),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.GothamPro),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.GothamPro),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.GothamPro),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.GothamPro),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.GothamPro),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.GothamPro),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.GothamPro),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.GothamPro),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.GothamPro),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.GothamPro),
)