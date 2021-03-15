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
        println("Got id in vm $id")
        getDetails(id)
        return restaurant
    }

    private suspend fun getDetails(id: String) {
        try {

            val response = restaurantsRepository.getRestaurantDetails(id)
            println("Got response $response")
            when (response) {

                is ResultWrapper.Success -> {
                    //ref: com.dash.doorlite.data.model.restaurant_details.RestaurantDetailsResponse
                    var details = Details(response.value.id.toString(),
                            if (response.value.deliveryFee == 0) "Free Delivery" else response.value.deliveryFee.toString(),
                            response.value.asapTime.toString(),
                            response.value.averageRating.toString(),
                            response.value.numberOfRatings.toString())
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