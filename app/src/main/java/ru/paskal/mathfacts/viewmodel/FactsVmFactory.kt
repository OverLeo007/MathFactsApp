package ru.paskal.mathfacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import ru.paskal.mathfacts.FactApp

class FactsVmFactory(private val factType: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val database = (checkNotNull(extras[APPLICATION_KEY]) as FactApp).database
        return FactsViewModel(database, factType) as T
    }
}