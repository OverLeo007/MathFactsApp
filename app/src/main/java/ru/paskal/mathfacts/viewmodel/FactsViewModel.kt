package ru.paskal.mathfacts.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import ru.paskal.mathfacts.api.FactGetter
import ru.paskal.mathfacts.database.FactsDatabase
import ru.paskal.mathfacts.database.toMathFact
import ru.paskal.mathfacts.models.MathFact
import ru.paskal.mathfacts.models.toMathFactEntity
import ru.paskal.mathfacts.utils.DropdownElements
import ru.paskal.mathfacts.utils.getDefaultInput
import ru.paskal.mathfacts.utils.isValidFactInput

class FactsViewModel(
    private val factsDatabase: FactsDatabase,
    private val factType: String
) : ViewModel() {
    private var currentSort: String by mutableStateOf(DropdownElements.Default)
    var currentFact: MathFact = MathFact(factText = "")
    var isLoading: Boolean by mutableStateOf(false)
    var currentInput: String by mutableStateOf(getDefaultInput(factType))
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
        val factEntity = currentFact.toMathFactEntity(factType)
        viewModelScope.launch {
            factsDatabase.dao.insert(factEntity)
            Log.d("VM SAVE", "Факт $factEntity сохранен")
        }
        loadFactsFromDb()
    }

    fun updateInput(text: String) {
        currentInput = text
        isInputCorrect = isValidFactInput(text, factType)
    }

    fun updateRating(newRating: Int) {
        currentFact.rating = newRating
    }


    fun generateAndSetFact() {
        viewModelScope.launch {
            isLoading = true
            try {
                val fact = FactGetter.generateFact(currentInput, factType)
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

}