package lamb.rebecca.domain.repository

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Result

interface MealRepository {

    suspend fun getRandomMeal(): Result<Meal>
    suspend fun getMealsByLetter(letter: String): Result<List<Meal>>

}