package com.example.trabajofinal.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM eventos")
    fun obtenerTodos(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(evento: Event)

    @Delete
    suspend fun eliminar(evento: Event)
}
