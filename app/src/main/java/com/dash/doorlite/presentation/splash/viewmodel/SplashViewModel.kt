package com.dash.doorlite.presentation.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dash.doorlite.core.AppManager
import com.dash.doorlite.presentation.splash.ui.SplashFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * The splash view model is responsible for exposing livedata for the location when the user approves the permission
 */
@HiltViewModel
class SplashViewModel @Inject constructor(private val appManager : AppManager) : ViewModel(){

    private val _state = MutableLiveData<SplashFragmentState>().apply { value = SplashFragmentState.Idle }
    val state: LiveData<SplashFragmentState>
        get() = _state


    init {
        viewModelScope.launch {
            appManager.locationState.collect{
                _state.postValue(SplashFragmentState.UserLocation(it))
            }
        }

    }

}