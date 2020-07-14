package lamb.rebecca.data.network.model

import lamb.rebecca.data.MealFaker
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class MealEntityTest {

    @Test
    fun canConvertToDomainModel() {
        val mealFaker = MealFaker()
        val mealDataModel = mealFaker.generateMealEntity()
        assertThat(mealDataModel.toDomain()).isEqualTo(mealFaker.generateMeal())
    }

}