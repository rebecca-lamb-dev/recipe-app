package lamb.rebecca.data.network

import lamb.rebecca.data.network.model.RandomMealResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface MealDbService {
    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealResponse

    companion object {
        fun mealDbService(retrofit: Retrofit): MealDbService =
            retrofit.create(MealDbService::class.java)
    }
}
