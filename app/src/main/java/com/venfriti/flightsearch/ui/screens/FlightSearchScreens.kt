package com.venfriti.flightsearch.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.venfriti.flightsearch.R
import com.venfriti.flightsearch.data.Airport
import com.venfriti.flightsearch.data.AirportSaver
import com.venfriti.flightsearch.ui.theme.PurpleGrey40
import com.venfriti.flightsearch.ui.theme.backgroundBlue
import com.venfriti.flightsearch.ui.theme.starFavoriteBackground


@Composable
fun FlightHomeScreen(viewModel: FlightSearchViewModel, contentPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(contentPadding)
    ) {
        val searchQuery by viewModel.searchQuery.collectAsState()
        val searchResults by viewModel.searchResults.collectAsState(initial = emptyList())
        val flightList by viewModel.flightList.collectAsState(initial = emptyList())
        var show by rememberSaveable { mutableStateOf(true) }


        var airport1 by rememberSaveable(stateSaver = AirportSaver) { mutableStateOf(Airport(1, "CFD", "Central cafe", 1000)) }
        val airport2 = Airport(2, "CFD", "Cengral cafe", 1000)
        var holder by rememberSaveable { mutableStateOf("") }

        SearchBar(
            searchQuery = holder,
            onSearch = { holder = it },
            onValueChange = {
                show = true
                holder = it
                viewModel.setSearchQuery(it)
            },
            onClear = {
                show = true
                holder = ""
            }
        )
//        TransitAirport(airport1, airport2)
        if (show){
            AirportGridList(searchResults?: emptyList(), onItemClick = {
                airport1 = it
                holder = airport1.iataCode
                show = false
            })
        } else {
            HideKeyboard()
            FlightTitle(holder)
            TransitGridList(airport1, flightList)
        }
    }
}

@Composable
fun HideKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearch: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit
) {

    val hint = "Search..."
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = {
            text = it
            onValueChange(text)
                        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(55.dp),
        placeholder = { Text(text = hint) },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundBlue,
            unfocusedContainerColor = backgroundBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(text)
            }
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "SearchIcon"
            )
        },
        trailingIcon = {
            IconButton(onClick = onClear) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear Search"
                )
            }
        },
        shape = ShapeDefaults.ExtraLarge
    )
}


@Composable
fun FlightTitle(flight: String) {
    Text(
        text = stringResource(R.string.flights_from, flight),
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun AirportTitle(airport: Airport, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = airport.iataCode,
            modifier = Modifier.width(40.dp),
            fontWeight = FontWeight.Black,
            fontSize = 13.sp,
            lineHeight = 13.sp
        )
        Text(
            text = airport.name,
            fontWeight = FontWeight.Light,
            fontSize = 13.sp,
            lineHeight = 13.sp
        )
    }
}

@Composable
fun FavoriteIcon(
    modifier: Modifier = Modifier,
    isFavoriteInitially: Boolean = false,
    onFavorite: () -> Unit,
    onUnFavorite: () -> Unit
) {
    var isFavorite by rememberSaveable { mutableStateOf(isFavoriteInitially) }

    IconButton(
        onClick = {
            isFavorite = !isFavorite
            if (isFavorite) {
                onFavorite()
            } else {
                onUnFavorite()
            }
        },
        modifier = modifier
    ) {
        if (isFavorite) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorite",
                tint = starFavoriteBackground,
                modifier = modifier
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Not Favorite",
                tint = PurpleGrey40 ,
                modifier = modifier
            )
        }
    }

}

@Composable
fun TransitAirport(
    airport1: Airport,
    airport2: Airport,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column( modifier = Modifier.fillMaxWidth(0.85f)) {
                Text(
                    text = "DEPART",
                    Modifier.padding(top = 8.dp , start = 16.dp, end = 16.dp, bottom = 8.dp),
                    fontSize = 13.sp,
                    lineHeight = 13.sp
                )
                AirportTitle(airport1)
                Text(
                    text = "ARRIVE",
                    Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                    fontSize = 13.sp,
                    lineHeight = 13.sp
                )
                AirportTitle(airport2)
                Spacer(Modifier.height(8.dp))
            }
            FavoriteIcon(modifier = Modifier.size(35.dp), onFavorite = {}, onUnFavorite = {})
        }
    }
}

@Composable
fun TransitGridList(
    port: Airport,
    airports: List<Airport>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        val listAirports = airports.minus(port)
        items(items = listAirports, key = {airport: Airport -> airport.id}) {
            airport ->
            TransitAirport(port, airport, modifier)
        }
    }
}

@Composable
fun AirportGridList(
    airports: List<Airport>,
    onItemClick: (Airport) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = airports, key = {airport: Airport -> airport.id }) {
            airport -> AirportTitle(
                airport,
                modifier
                    .clickable { onItemClick(airport) }
                    .padding(vertical = 5.dp)
            )
        }
    }

}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(searchQuery = "checker", onSearch = { }, onValueChange = { }, onClear = {})
}

@Preview(showBackground = true)
@Composable
fun TransitAirportPreview() {
    val airport1 = Airport(1, "CFC", "Central cafe 1", 1000)
    val airport2 = Airport(2, "CFD", "Central cafe 2", 1000)
    TransitAirport(airport1, airport2)
}

@Preview(showBackground = true)
@Composable
fun AirportTitlePreview() {
    val airport1 = Airport(3, "CFC", "Central Cafe", 500)
    AirportTitle(airport1)
}

@Preview(showBackground = true)
@Composable
fun GridListPreview() {
    val airports = listOf<Airport>(
        Airport(1, "CFC", "Central cafe 1", 1000),
        Airport(2, "CFD", "Central cafe 2", 1000),
        Airport(3, "CFE", "Central cafe 3", 1000),
        Airport(4, "CFF", "Central cafe 4", 1000),
        Airport(5, "CFG", "Central cafe 5", 1000),
        Airport(6, "CFH", "Central cafe 6", 1000),
        Airport(7, "CFI", "Central cafe 7", 1000),
    )
    AirportGridList(airports, onItemClick = { })
}