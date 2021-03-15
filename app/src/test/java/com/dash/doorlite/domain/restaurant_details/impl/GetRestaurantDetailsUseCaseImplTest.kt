package com.dash.doorlite.domain.restaurant_details.impl

import com.dash.doorlite.CoroutineTestRule
import com.dash.doorlite.FakeRestaurantsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class GetRestaurantDetailsUseCaseImplTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun useRunBlocking() = runBlocking<Unit> {
        val repository = FakeRestaurantsRepository()
        repository.getNearbyRestaurants()

        //TODO

    }
}