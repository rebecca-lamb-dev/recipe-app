package lamb.rebecca.data.network.model

import com.squareup.moshi.JsonDataException
import lamb.rebecca.data.MealFaker
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class RandomMealListResponseTest {

    @Test
    fun convertToDomainObjectThrowsErrorOnIncorrectNumberOfEntries() {
        val mealFaker = MealFaker()
        val randomMealListResponse = RandomMealListResponse(
            listOf(
                mealFaker.generateMealEntity(),
                mealFaker.generateMealEntity()
            )
        )

        assertThatThrownBy { randomMealListResponse.toDomain() }
            .isInstanceOf(JsonDataException::class.java)
    }

    @Test
    fun convertToDomainObjectThrowsErrorOnMissingEntry() {
        val randomMealListResponse = RandomMealListResponse(
            listOf()
        )

        assertThatThrownBy { randomMealListResponse.toDomain() }
            .isInstanceOf(JsonDataException::class.java)
    }

    @Test
    fun canConvertToDomainObject() {
        val mealFaker = MealFaker()
        val randomMealListResponse = RandomMealListResponse(
            listOf(
                mealFaker.generateMealEntity()
            )
        )

        assertThat(randomMealListResponse.toDomain())
            .isEqualTo(
                mealFaker.generateMeal()
            )
    }


}