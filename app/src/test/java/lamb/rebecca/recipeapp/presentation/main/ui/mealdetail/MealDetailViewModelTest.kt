package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.CoroutinesTestRule
import lamb.rebecca.recipeapp.getOrAwaitValue
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class MealDetailViewModelTest {

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var couroutineRule = CoroutinesTestRule()

    val getRandomMealUseCase = mockk<GetRandomMealUseCase>()

    @ExperimentalCoroutinesApi
    @Test
    fun canGetRandomMeal() {
        val expectedMeal =
            Meal(
                "12345",
                "cake",
                listOf(MeasuredIngredient("ingredient", "measurement")),
                "thumb",
                "category",
                "area",
                "1\r\n2\r\n3"
            )

        coEvery { getRandomMealUseCase() } returns Success(expectedMeal)
        val mealDetailViewModel = MealDetailViewModel(getRandomMealUseCase)

        val result = mealDetailViewModel.meal.getOrAwaitValue()
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