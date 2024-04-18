package ru.paskal.mathfacts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fact: MathFactEntity)

    @Update
    suspend fun update(fact: MathFactEntity)

    @Delete
    suspend fun delete(fact: MathFactEntity)


    @Query("SELECT * FROM facts_table WHERE type = :type ORDER BY rating DESC")
    fun getFactsSortedByRatingDesc(type: String): Flow<List<MathFactEntity>>

    @Query("SELECT * FROM facts_table WHERE type = :type ORDER BY rating ASC")
    fun getFactsSortedByRatingAsc(type: String): Flow<List<MathFactEntity>>

    @Query("SELECT * FROM facts_table WHERE type = :type ORDER BY id DESC")
    fun getFactsSortedByIdDesc(type: String): Flow<List<MathFactEntity>>
}