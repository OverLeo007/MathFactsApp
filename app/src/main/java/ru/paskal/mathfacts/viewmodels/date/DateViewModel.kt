package ru.paskal.mathfacts.viewmodels.date

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.paskal.mathfacts.FactApp
import ru.paskal.mathfacts.api.NumbersApi
import ru.paskal.mathfacts.api.createRetrofit
import ru.paskal.mathfacts.database.FactsDatabase
import ru.paskal.mathfacts.database.MathFactEntity
import ru.paskal.mathfacts.database.toMathFact
import ru.paskal.mathfacts.models.MathFact
import ru.paskal.mathfacts.models.toMathFactEntity
import ru.paskal.mathfacts.utils.DropdownElements
import ru.paskal.mathfacts.utils.FactTypes
import ru.paskal.mathfacts.utils.getCurrentDate
import ru.paskal.mathfacts.utils.isValidDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateViewModel(
    private val numbersApi: NumbersApi,
    private val factsDatabase: FactsDatabase
) : ViewModel() {
    private val factType = FactTypes.Date
    private var currentSort: String by mutableStateOf(DropdownElements.Default)
    var currentFact: MathFact = MathFact(factText = "")
    var isLoading: Boolean by mutableStateOf(false)
    var currentInput: String by mutableStateOf(getCurrentDate())
    var isInputCorrect: Boolean = true
    var savedFacts: List<MathFact> by mutableStateOf(mutableListOf())


    private fun loadFactsFromDb() {
        viewModelScope.launch {
            val facts = when (currentSort) {
                DropdownElements.ToLower -> factsDatabase.dao.getFactsSortedByRatingDesc(factType)
                DropdownElements.ToHigher -> factsDatabase.dao.getFactsSortedByRatingAsc(factType)
                DropdownElements.ToAddOrder -> factsDatabase.dao.getFactsSortedByIdDesc(factType)
                else -> throw IllegalArgumentException("Unknown sort type: $currentSort")
            }.firstOrNull() ?: emptyList()

            savedFacts = facts.map { it.toMathFact() }
        }

    }

    fun updateSorting(newSortType: String) {
        currentSort = newSortType
        Log.d("SORTING", currentSort)
        loadFactsFromDb()

    }

    fun deleteFact(fact: MathFact) {
        Log.d("VM DELETE", "Удаление факта")
        viewModelScope.launch {
            factsDatabase.dao.delete(fact.toMathFactEntity(factType))
            Log.d("VM DELETE", "Факт $fact удален")
        }
        loadFactsFromDb()
    }

    fun saveFact(fact: MathFact) {
        Log.d("VM Update", "Обновление факта")
        viewModelScope.launch {
            factsDatabase.dao.update(fact.toMathFactEntity(factType))
            Log.d("VM Update", "Факт $fact обновлен")
        }
        loadFactsFromDb()
    }

    fun saveCurrentFact() {

        Log.d("VM SAVE", "Сохранение факта")
        val factEntity = MathFactEntity(
            rating = currentFact.rating,
            factText = currentFact.factText,
            type = factType
        )
        viewModelScope.launch {
            factsDatabase.dao.insert(factEntity)
            Log.d("VM SAVE", "Факт $factEntity сохранен")
        }
        loadFactsFromDb()
    }

    fun updateInput(text: String) {
        currentInput = text
        isInputCorrect = isValidDate(text)
    }

    fun updateRating(newRating: Int) {
        currentFact.rating = newRating
    }

    private suspend fun generateFact(dateStr: String): String? {
        return withContext(Dispatchers.IO) {
            val dateWithYear = "$dateStr.2022" // add a default year
            val date = LocalDate.parse(
                dateWithYear,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
            ) // use a date pattern that includes the year
            val day = date.dayOfMonth
            val month = date.monthValue
            try {
                val call = numbersApi.getDateFact(month, day)
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

    fun generateAndSetFact() {
        viewModelScope.launch {
            isLoading = true
            try {
                val fact = generateFact(currentInput)
                if (fact != null) {
                    currentFact = MathFact(rating = currentFact.rating, factText = fact)
                }
            } catch (e: Exception) {
                Log.e("FACT", "Error generating fact", e)
            } finally {
                isLoading = false
            }
        }
    }

    init {
        loadFactsFromDb()
        generateAndSetFact()
    }


    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val numbersApi = createRetrofit().create(NumbersApi::class.java)
                val database = (checkNotNull(extras[APPLICATION_KEY]) as FactApp).database
                return DateViewModel(numbersApi, database) as T
            }
        }
    }
}