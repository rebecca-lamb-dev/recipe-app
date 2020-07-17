package lamb.rebecca.recipeapp.presentation.main.ui.model

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MealModelTest {

    @Test
    fun mapFromDomain() {
        val meal = Meal(
            "id", "name", listOf(MeasuredIngredient("ing", "mea")),
            "thumb", "cat", "area", "a\r\nb\r\nc"
        )

        assertThat(MealModel.fromDomain(meal)).isEqualTo(
            MealModel(
                "id", "name", listOf(MeasuredIngredientModel("ing", "mea")),
                "thumb", "cat", "area", listOf("a", "b", "c")
            )
        )
    }

    @Test
    fun removeEmptyLinesFromInstructions() {
        val meal = Meal(
            "id", "name", listOf(MeasuredIngredient("ing", "mea")),
            "thumb", "cat", "area", "a\r\n\r\nb\r\n\r\nc"
        )

        assertThat(MealModel.fromDomain(meal)).isEqualTo(
            MealModel(
                "id", "name", listOf(MeasuredIngredientModel("ing", "mea")),
                "thumb", "cat", "area", listOf("a", "b", "c")
            )
        )
    }


}