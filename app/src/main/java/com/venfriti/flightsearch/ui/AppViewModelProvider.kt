package com.venfriti.flightsearch.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venfriti.flightsearch.FlightSearchApplication
import com.venfriti.flightsearch.ui.screens.FlightSearchViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FlightSearchViewModel(
                this.createSavedStateHandle(),
                flightApplication().container.flightRepository
            )
        }
    }
}

fun CreationExtras.flightApplication(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)