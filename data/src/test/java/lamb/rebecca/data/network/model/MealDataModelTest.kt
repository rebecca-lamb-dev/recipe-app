package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class MealDataModelTest {

    @Test
    fun canConvertToDomainModel() {
        val mealDataModel = MealDataModel(
            "12345", "bread", listOf(
                MeasuredIngredientDataModel("ingredient", "measurement")
            )
        )
        assertThat(mealDataModel.toModel()).isEqualTo(
            Meal(
                "12345", "bread", listOf(
                    MeasuredIngredient("ingredient", "measurement")
                )
            )
        )
    }

}