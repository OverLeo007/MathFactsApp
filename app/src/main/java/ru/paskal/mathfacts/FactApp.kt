package ru.paskal.mathfacts

import android.app.Application
import ru.paskal.mathfacts.database.FactsDatabase

class FactApp : Application() {
    val database by lazy { FactsDatabase.createDataBase(this)}
}