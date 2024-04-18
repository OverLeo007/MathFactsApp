package ru.paskal.mathfacts.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.paskal.mathfacts.models.MathFact

@Entity(
    tableName = "facts_table",
    indices = [Index(value = ["factText"], unique = true)]
)

data class MathFactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rating: Int,

    val factText: String,
    val type: String
)

fun MathFactEntity.toMathFact(): MathFact {
    return MathFact(id = id, rating = rating, factText = factText)
}

