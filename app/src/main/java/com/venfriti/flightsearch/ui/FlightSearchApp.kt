package com.venfriti.flightsearch.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.venfriti.flightsearch.ui.screens.FlightHomeScreen
import com.venfriti.flightsearch.ui.screens.FlightSearchViewModel
import com.venfriti.flightsearch.ui.theme.lightBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val airportUiState by viewModel.airportUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { FlightTopAppBar(scrollBehavior = scrollBehavior ) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            FlightHomeScreen(
                viewModel = viewModel,
                contentPadding = it
            )
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier){
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Flight Search",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = lightBlue
        ),
        modifier = modifier
    )
}