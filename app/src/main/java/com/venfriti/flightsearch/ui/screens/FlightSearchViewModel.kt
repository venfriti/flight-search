package com.venfriti.flightsearch.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venfriti.flightsearch.FlightSearchApplication
import com.venfriti.flightsearch.data.Airport
import com.venfriti.flightsearch.data.FlightDao
import kotlinx.coroutines.flow.Flow


class FlightSearchViewModel(private val flightDao: FlightDao): ViewModel(){

    fun getFullList(): Flow<List<Airport>> = flightDao.getAllFlights()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(application.database.flightDao())
            }
        }
    }
}