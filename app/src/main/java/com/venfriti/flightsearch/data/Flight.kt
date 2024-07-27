package com.venfriti.flightsearch.data

import androidx.compose.runtime.saveable.Saver
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "iata_code") val iataCode: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "passengers") val passengers: Int,
)

val AirportSaver = Saver<Airport, Any>(
    save = { airport ->
        listOf(airport.id, airport.iataCode, airport.name, airport.passengers)
    },
    restore = { data ->
        val (id, iataCode, name, passengers) = data as List<*>
        Airport(id as Int, iataCode as String, name as String, passengers as Int)
    }
)


@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "departure_code") val departureCode: String,
    @ColumnInfo(name = "destination_code") val destinationCode: String,
)