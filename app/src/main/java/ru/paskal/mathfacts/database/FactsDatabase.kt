package ru.paskal.mathfacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        MathFactEntity::class
    ],
    version = 2
)
abstract class FactsDatabase : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        fun createDataBase(context: Context): FactsDatabase {
            return Room.databaseBuilder(
                context,
                FactsDatabase::class.java,
                "main.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}