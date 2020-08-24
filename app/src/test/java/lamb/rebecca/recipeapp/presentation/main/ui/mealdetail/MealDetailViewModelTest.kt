package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class MealDetailViewModelTest {

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @Test
    fun getMeal() {
        val expectedMeal =
            MealModel(
                "12345",
                "cake",
                listOf(MeasuredIngredientModel("ingredient", "measurement")),
                "thumb",
                "category",
                "area",
                listOf("1", "2", "3")
            )

        val mealDetailViewModel =
            MealDetailViewModel(SavedStateHandle(mapOf(Pair("meal_id", expectedMeal))))

        val result = mealDetailViewModel.meal.value
        assertThat(result).isEqualTo(
            MealModel(
                "12345",
                "cake",
                listOf(MeasuredIngredientModel("ingredient", "measurement")),
                "thumb",
                "category",
                "area",
                listOf("1", "2", "3")
            )
        )
    }

}