package com.dash.doorlite.domain.restaurant.impl

import com.dash.doorlite.core.presentation.viewmodel.Location
import com.dash.doorlite.core.service.ResultWrapper
import com.dash.doorlite.data.model.PopularItem
import com.dash.doorlite.data.repository.RestaurantsRepository
import com.dash.doorlite.domain.restaurant.GetNearbyRestaurantsUseCase
import com.dash.doorlite.domain.restaurant.model.Item
import com.dash.doorlite.domain.restaurant.model.Restaurant
import com.dash.doorlite.domain.restaurant.model.Restaurants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException
import java.text.DecimalFormat
import javax.inject.Inject


private const val STARTING_OFFSET = 0

class GetNearbyRestaurantsUseCaseImpl @Inject constructor(private val restaurantsRepository: RestaurantsRepository) :
        GetNearbyRestaurantsUseCase {

    private val _cache = mutableListOf<Restaurant>()

    private val restaurants = MutableSharedFlow<Restaurants>(replay = 1)
    private var isRequestInProgress = false
    private var nextOffSet = STARTING_OFFSET
    private var isEnd = false
    private var isSecondLast = false


    override suspend fun requestMore(location: Location?) {
        if (isRequestInProgress || isEnd) return
        requestData(location!!)

    }


    /**
     * Map the popular items
     */
    private fun getPopularItems(items: List<PopularItem>): List<Item> {

        val dec = DecimalFormat("#.##")
        return items.map { item ->
            Item(item.id.toString(), item.name, item.description, item.imgUrl, String.format(java.util.Locale.US, "%.002f", item.price.toDouble() / 100))
        }

    }

    private suspend fun requestData(location: Location) {
        isRequestInProgress = true
        try {
            when (val response = restaurantsRepository.getNearbyRestaurants(
                    location.latitude,
                    location.longitude,
                    nextOffSet.toString(),
                    PAGE_SIZE.toString(),
            )) {
                is ResultWrapper.Success -> {
                    //ref: com.dash.doorlite.data.model.RestaurantsResponse
                    nextOffSet = response.value.nextOffset
                    val stores = response.value.stores.map {
                        Restaurant(
                                it.id.toString(),
                                it.name,
                                it.description,
                                it.coverImgUrl,
                                it.headerImgUrl,
                                it.numRatings,
                                it.distanceFromConsumer,
                                it.displayDeliveryFee,
                                if (it.menus.isNotEmpty()) getPopularItems(it.menus[0].popularItems) else emptyList() // ambiguity here, but this demo will do
                        )
                    }
                    _cache.addAll(stores)
                    _cache.sortWith { x, y -> x.distance.toInt().compareTo(y.distance.toInt()) }

                    restaurants.emit(Restaurants.Success(_cache))
                    isSecondLast = response.value.nextOffset == 0

                    if (_cache.size == response.value.numResults) {
                        isEnd = true
                    }
                }

            }

            isRequestInProgress = false

        } catch (exception: IOException) {
            restaurants.emit(Restaurants.Error(exception))
        } catch (exception: HttpException) {
            restaurants.emit(Restaurants.Error(exception))
        }


    }

    companion object {
        private const val PAGE_SIZE = 50
    }

    override suspend fun getNearbyRestaurantsStream(location: Location): Flow<Restaurants> {
        requestData(location)
        return restaurants
    }
}






