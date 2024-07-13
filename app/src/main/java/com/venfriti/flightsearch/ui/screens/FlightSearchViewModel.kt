package com.venfriti.flightsearch.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venfriti.flightsearch.FlightSearchApplication
import com.venfriti.flightsearch.data.Airport
import com.venfriti.flightsearch.data.FlightDao
import com.venfriti.flightsearch.data.FlightRepository
import kotlinx.coroutines.flow.Flow


class FlightSearchViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository
): ViewModel(){



}