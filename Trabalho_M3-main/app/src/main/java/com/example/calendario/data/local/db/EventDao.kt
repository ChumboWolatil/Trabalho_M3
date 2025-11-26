package com.example.calendario.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM events ORDER BY timestamp DESC")
    fun getEvents(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: com.example.calendario.data.local.EventEntity)

    @Delete
    suspend fun deleteEvent(event: EventEntity)
}

