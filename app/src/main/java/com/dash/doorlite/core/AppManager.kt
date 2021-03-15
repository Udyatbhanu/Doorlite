package com.dash.doorlite.core

import android.content.Context
import com.dash.doorlite.core.presentation.viewmodel.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppManager constructor(@ApplicationContext context: Context) {
    var location: Location? = null
    private val _locationState = MutableSharedFlow<Location>()
    val locationState = _locationState.asSharedFlow()


    suspend fun updateLocation(location: Location) {
        _locationState.emit(location)
    }

}