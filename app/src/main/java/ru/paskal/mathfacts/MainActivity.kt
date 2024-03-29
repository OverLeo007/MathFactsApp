package ru.paskal.mathfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.paskal.mathfacts.ui.theme.MathFactsTheme

import ru.paskal.mathfacts.ui.views.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathFactsTheme {
                MainScreen()
            }
        }
    }

}





