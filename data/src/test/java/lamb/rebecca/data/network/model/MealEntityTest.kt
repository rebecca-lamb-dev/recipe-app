package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class MealEntityTest {

    @Test
    fun canConvertToDomainModel() {
        val mealDataModel = MealEntity("12345", "bread", listOf(
                MeasuredIngredientEntity("ingredient", "measurement")
            ),  "thumnb"
        )
        assertThat(mealDataModel.toDomain()).isEqualTo(
            Meal(
                "12345", "bread", listOf(
                    MeasuredIngredient("ingredient", "measurement")
                ), "thumnb"
            )
        )
    }

}