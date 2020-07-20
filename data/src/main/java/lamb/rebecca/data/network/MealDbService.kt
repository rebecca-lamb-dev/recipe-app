package lamb.rebecca.data.network

import lamb.rebecca.data.network.model.MealListResponse
import lamb.rebecca.data.network.model.RandomMealListResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbService {
    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealListResponse

    @GET("search.php")
    suspend fun getMealsForLetter(@Query("f") letter: String): MealListResponse

    companion object {
        fun mealDbService(retrofit: Retrofit): MealDbService =
            retrofit.create(MealDbService::class.java)
    }
}
