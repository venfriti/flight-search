package com.venfriti.flightsearch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.venfriti.flightsearch.R


@Composable
fun FlightHomeScreen(viewModel: FlightSearchViewModel, contentPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(contentPadding)
    ) {
        Text(
            text = "Hello, How are you doing",
            modifier = Modifier.padding(16.dp),
        )
        FlightTitle("FCO")
        SearchBar(hint = "Search...", onSearch = {})
    }

}

@Composable
fun SearchBar(hint: String, onSearch: (String) -> Unit ) {
    var text by rememberSaveable {mutableStateOf("")}

    TextField(
        value = text,
        onValueChange = { text = it},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        placeholder = { Text(text = hint) },
        singleLine = true,  
        maxLines = 1,
        colors = TextFieldDefaults.colors(
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
                imageVector = Icons.Default.Search,
                contentDescription = "SearchIcon"
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = {text = ""}) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Text"
                    )
                }
            }
        }
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