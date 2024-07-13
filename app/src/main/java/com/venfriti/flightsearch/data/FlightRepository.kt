package com.venfriti.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightRepository {

    fun getAirport(searchName: String): Flow<List<Airport>?>

}