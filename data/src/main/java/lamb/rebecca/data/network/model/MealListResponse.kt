package lamb.rebecca.data.network.model

import com.squareup.moshi.JsonDataException
import lamb.rebecca.domain.model.Meal

data class MealListResponse(val meals: List<MealEntity>) : Entity<List<Meal>> {

    override fun toDomain(): List<Meal> = meals.map { it.toDomain() }

}

data class RandomMealListResponse(val meals: List<MealEntity>) : Entity<Meal> {

    override fun toDomain(): Meal {
        val result = meals.map { it.toDomain() }
        if (result.size != 1) {
            throw JsonDataException("Incorrect size of results ${result.size}")
        }
        return result[0]
    }

}