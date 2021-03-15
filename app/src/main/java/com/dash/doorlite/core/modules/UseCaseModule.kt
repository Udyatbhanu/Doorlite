package com.dash.doorlite.core.modules

import com.dash.doorlite.domain.restaurant.GetNearbyRestaurantsUseCase
import com.dash.doorlite.domain.restaurant.impl.GetNearbyRestaurantsUseCaseImpl
import com.dash.doorlite.domain.restaurant_details.GetRestaurantDetailsUseCase
import com.dash.doorlite.domain.restaurant_details.impl.GetRestaurantDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun getNearbyRestaurantsUseCase(getNearbyRestaurantsUseCase: GetNearbyRestaurantsUseCaseImpl): GetNearbyRestaurantsUseCase

    @Binds
    abstract fun getRestaurantDetailsUseCase(getRestaurantDetailsUseCase: GetRestaurantDetailsUseCaseImpl): GetRestaurantDetailsUseCase
}