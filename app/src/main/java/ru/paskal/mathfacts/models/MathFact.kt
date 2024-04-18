package ru.paskal.mathfacts.models

import ru.paskal.mathfacts.database.MathFactEntity


data class MathFact(
    val id: Int = 0,
    var rating: Int = 0,
    val factText: String,
)

fun MathFact.toMathFactEntity(type: String): MathFactEntity {
    return MathFactEntity(
        id = id,
        rating = rating,
        factText = factText,
        type = type
    )
}
