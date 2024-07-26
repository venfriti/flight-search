package com.venfriti.flightsearch.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.venfriti.flightsearch.FlightSearchApplication
import com.venfriti.flightsearch.data.Airport
import com.venfriti.flightsearch.data.FlightDao
import com.venfriti.flightsearch.data.FlightRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class FlightSearchViewModel(
    savedStateHandle: SavedStateHandle,
    flightRepository: FlightRepository
): ViewModel(){

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults = _searchQuery.flatMapLatest { query ->
        flightRepository.getAirport(query)
    }
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

//    val airportUiState: StateFlow<AirportUiState> =
//        flightRepository.getAllAirports().map { AirportUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = AirportUiState()
//            )
//
//
//    val secondOption : (search: String) -> StateFlow<AirportUiState> = { search ->
//        flightRepository.getAirport(search).map { AirportUiState(it ?: emptyList()) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.Eagerly,
//                initialValue = AirportUiState()
//            )
//    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }



}

data class AirportUiState(
    val airportList: List<Airport> = listOf()
)