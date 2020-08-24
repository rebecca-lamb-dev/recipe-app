package lamb.rebecca.recipeapp.presentation.main.ui.meallist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetMealsByLetterUseCaseImpl
import lamb.rebecca.recipeapp.CoroutinesTestRule
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MealsViewModelTest {

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val getMealsByLetterUseCase: GetMealsByLetterUseCaseImpl = mockk()

    @Test
    fun canFetchListOfMeals() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { getMealsByLetterUseCase("b") }.returns(
            Success(
                listOf(
                    Meal(
                        "12345",
                        "cake",
                        listOf(MeasuredIngredient("ingredient", "measurement")),
                        "thumb",
                        "category",
                        "area",
                        "1\r\n2\r\n3"
                    )
                )
            )
        )

        val mealsViewModel = MealsViewModel(getMealsByLetterUseCase, coroutinesTestRule.testDispatcher)

        val result = mealsViewModel.meals.value
        val expectedResult = MealModel(
            "12345",
            "cake",
            listOf(MeasuredIngredientModel("ingredient", "measurement")),
            "thumb",
            "category",
            "area",
            listOf("1", "2", "3")
        )

        assertThat(result).isEqualTo(listOf(expectedResult))
    }

}