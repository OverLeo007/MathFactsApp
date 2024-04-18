package ru.paskal.mathfacts.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun isValidDate(input: String): Boolean {
    return try {
        val dateWithYear = "$input.2022" // add a default year
        LocalDate.parse(dateWithYear, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun getCurrentDate(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"))
}