package com.dash.doorlite.core.presentation.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dash.doorlite.core.AppManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch
import javax.inject.Inject

@Parcelize
data class Location(val latitude: String, val longitude: String) : Parcelable


@HiltViewModel
class SessionViewModel @Inject constructor(private val appManager: AppManager) : ViewModel() {


    fun initializeState(location: Location) {
        viewModelScope.launch {
            appManager.updateLocation(location)
        }

    }
}