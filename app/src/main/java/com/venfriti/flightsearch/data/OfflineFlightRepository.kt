package com.venfriti.flightsearch.data

import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val flightDao: FlightDao): FlightRepository {

    override fun getAirport(searchName: String): Flow<List<Airport>?> =  flightDao.getAirport(searchName)
}