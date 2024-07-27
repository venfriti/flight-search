package com.venfriti.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FlightDao{
    @Query("SELECT * FROM airport ORDER BY id ASC")
    fun getAllFlights(): Flow<List<Airport>>

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM airport WHERE name LIKE '%' || :searchName || '%'")
    fun getAirport(searchName: String): Flow<List<Airport>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(favorite: Favorite)

    @Delete
    suspend fun removeFromFavorites(favorite: Favorite)
}