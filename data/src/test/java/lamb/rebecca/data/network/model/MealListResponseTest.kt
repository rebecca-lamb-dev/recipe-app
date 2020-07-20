package lamb.rebecca.data.network.model

import lamb.rebecca.data.MealFaker
import org.assertj.core.api.Assertions
import org.junit.Test

class MealListResponseTest {

    @Test
    fun canConvertToDomainObject() {
        val mealFaker = MealFaker()
        val mealListResponse = MealListResponse(
            listOf(
                mealFaker.generateMealEntity()
            )
        )

        Assertions.assertThat(mealListResponse.toDomain())
            .isEqualTo(
                listOf(
                    mealFaker.generateMeal()
                )
            )
    }


}