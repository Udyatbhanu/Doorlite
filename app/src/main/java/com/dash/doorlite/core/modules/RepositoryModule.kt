package com.dash.doorlite.core.modules

import com.dash.doorlite.core.network.RestaurantsApi
import com.dash.doorlite.data.repository.RestaurantsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {


    @Provides
    @ViewModelScoped
    fun provideRestaurantsRepository(api : RestaurantsApi) : RestaurantsRepository {
        return RestaurantsRepository(api)
    }
}