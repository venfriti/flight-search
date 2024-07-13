package com.venfriti.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FlightDao{
    @Query("SELECT * FROM airport_table ORDER BY id ASC")
    fun getAllFlights(): Flow<List<Airport>>

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM airport_table WHERE name LIKE '%' || :searchName || '%'")
    fun getAirport(searchName: String): Flow<List<Airport>>
}