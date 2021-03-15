package com.dash.doorlite.domain.restaurant_details.impl

import com.dash.doorlite.core.service.ResultWrapper
import com.dash.doorlite.data.repository.RestaurantsRepository
import com.dash.doorlite.domain.restaurant_details.GetRestaurantDetailsUseCase
import com.dash.doorlite.domain.restaurant_details.model.Details
import com.dash.doorlite.domain.restaurant_details.model.RestaurantDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRestaurantDetailsUseCaseImpl @Inject constructor(private val restaurantsRepository: RestaurantsRepository) :
        GetRestaurantDetailsUseCase {

    private val restaurant = MutableSharedFlow<RestaurantDetails>(replay = 1)
    override suspend fun getRestaurantDetails(id: String): Flow<RestaurantDetails> {
        getDetails(id)
        return restaurant
    }


    /**
     * Build the tags
     */
    private fun buildTags(tagsList: List<String>): String {
        var tags = ""
        tagsList.map { tag ->
            tags = tags.plus(tag).plus(" | ")

        }
        return tags.substring(0, tags.length - 2) // remove last 2 including the space
    }

    private suspend fun getDetails(id: String) {
        try {

            when (val response = restaurantsRepository.getRestaurantDetails(id)) {

                is ResultWrapper.Success -> {
                    //ref: com.dash.doorlite.data.model.restaurant_details.RestaurantDetailsResponse
                    var details = Details(response.value.id.toString(),
                            if (response.value.deliveryFee == 0) "Free Delivery" else response.value.deliveryFee.toString(),
                            response.value.asapTime.toString(),
                            response.value.averageRating.toString(),
                            response.value.numberOfRatings.toString(),
                            buildTags(response.value.tags.take(4)))
                    restaurant.emit(RestaurantDetails.Success(details))
                }


            }

        } catch (exception: IOException) {
            restaurant.emit(RestaurantDetails.Error(exception))
        } catch (exception: HttpException) {
            restaurant.emit(RestaurantDetails.Error(exception))
        }


    }
}