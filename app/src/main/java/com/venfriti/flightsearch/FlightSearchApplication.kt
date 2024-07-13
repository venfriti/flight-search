package com.venfriti.flightsearch

import android.app.Application
import com.venfriti.flightsearch.data.AppContainer
import com.venfriti.flightsearch.data.AppDataContainer
import com.venfriti.flightsearch.data.AppDatabase

class FlightSearchApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}