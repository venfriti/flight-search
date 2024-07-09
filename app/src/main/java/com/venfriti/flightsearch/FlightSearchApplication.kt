package com.venfriti.flightsearch

import android.app.Application
import com.venfriti.flightsearch.data.AppDatabase

class FlightSearchApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}