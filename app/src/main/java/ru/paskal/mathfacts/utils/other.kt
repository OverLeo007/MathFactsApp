package ru.paskal.mathfacts.utils

import androidx.core.text.isDigitsOnly
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.random.Random
import kotlin.random.nextInt

fun isValidFactInput(input: String, factType: String): Boolean {
    return when (factType) {
        FactTypes.Date -> {
            try {
                val dateWithYear = "$input.2022" // add a default year
                LocalDate.parse(dateWithYear, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                true
            } catch (e: DateTimeParseException) {
                false
            }
        }
        FactTypes.Math -> input.isDigitsOnly()
        FactTypes.Trivia -> input.isDigitsOnly()
        FactTypes.Map -> input.isDigitsOnly()
        else -> throw IllegalArgumentException("Unknown fact type: $factType")
    }
}

fun getCurrentDate(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"))
}

fun getDefaultInput(factType: String): String {
    return when (factType) {
        FactTypes.Date -> getCurrentDate()
        FactTypes.Math -> Random.nextInt(1..1000).toString()
        FactTypes.Trivia -> Random.nextInt(1..1000).toString()
        FactTypes.Map -> Random.nextInt(1..99).toString()
        else -> throw IllegalArgumentException("Unknown fact type: $factType")
    }
}