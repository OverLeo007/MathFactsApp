package ru.paskal.mathfacts.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.paskal.mathfacts.utils.FactTypes
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FactGetter {
    companion object {
        private val api = createRetrofit().create(NumbersApi::class.java)

        suspend fun generateFact(input: String, factType: String): String? {
            return when (factType) {
                FactTypes.Date -> getDateFact(input)
                FactTypes.Math -> getMathFact(input.toInt())
                FactTypes.Trivia -> getTriviaFact(input.toInt())
                FactTypes.Map -> getMathFact(input.toInt())
                else -> throw IllegalArgumentException("Unknown fact type: $factType")
            }
        }

        private suspend fun getDateFact(dateStr: String): String? {
            return withContext(Dispatchers.IO) {
                val dateWithYear = "$dateStr.2022"
                val date = LocalDate.parse(
                    dateWithYear,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy")
                )
                val day = date.dayOfMonth
                val month = date.monthValue
                try {
                    val call = api.getDateFact(month, day)
                    val response = call.execute()
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }

        private suspend fun getTriviaFact(number: Int): String? {
            return withContext(Dispatchers.IO) {
                try {
                    val call = api.getTrivia(number)
                    val response = call.execute()
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }

        private suspend fun getMathFact(number: Int): String? {
            return withContext(Dispatchers.IO) {
                try {
                    val call = api.getMathFact(number)
                    val response = call.execute()
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}